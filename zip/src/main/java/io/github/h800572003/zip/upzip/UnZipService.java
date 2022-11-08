package io.github.h800572003.zip.upzip;

import io.github.h800572003.zip.IZip;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class UnZipService implements IUnZipService {

    private IRemoveStrategy removeStrategy;


    protected IRemoveStrategy get(Path path) {
        if (this.removeStrategy == null) {
            return new CommonRemoveStrategy(path);
        }
        return this.removeStrategy;
    }


    @Override
    public void execute(InputStream inputStream, UnZipStrategy unZipStrategy) {
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            unZipStrategy.handle(new UnZipContext(zipInputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    interface INameFilter {
        boolean filter(String name);
    }

    class UnZipContext implements IUnZipContext {
        private ZipInputStream zipInputStream;


        private List<IZip> zips = new ArrayList<>();


        public UnZipContext(ZipInputStream zipInputStream) {
            this.zipInputStream = zipInputStream;
        }


        @Override
        public List<IZip> getZips(Predicate<String> predicate) {
            try {
                ZipEntry nextEntry;
                while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                    this.zips.add(new MyZip(nextEntry, zipInputStream));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this.zips.stream().filter(i -> predicate.test(i.getName())).collect(Collectors.toList());


        }

    }

    class MyZip implements IZip {

        private ZipInputStream zipInputStream;
        private ZipEntry zipEntry;


        public MyZip(ZipEntry zipEntry, ZipInputStream zipInputStream) throws IOException {
            this.zipEntry = zipEntry;
            this.zipInputStream = zipInputStream;

        }

        @Override
        public boolean isDir() {
            return zipEntry.isDirectory();
        }


        @Override
        public String getName() {
            return zipEntry.getName();
        }

        @Override
        public void remove(IRemoveStrategy removeStrategy) throws IOException {
            removeStrategy.remove(zipEntry, zipInputStream);
        }

        @Override
        public Long getSize() {
            return zipEntry.getSize();
        }


    }


}
