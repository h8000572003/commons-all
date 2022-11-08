package io.github.h800572003.zip.upzip;

import io.github.h800572003.zip.IZip;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

@Slf4j
public class AllFileUnZipStrategy implements UnZipStrategy {

    private final IRemoveStrategy removeStrategy;
    private final Predicate<String> predicate;

    public AllFileUnZipStrategy(IRemoveStrategy removeStrategy, Predicate<String> predicate) {
        this.removeStrategy = removeStrategy;
        this.predicate = predicate;
    }

    @Override
    public void handle(IUnZipContext context) throws IOException {
        for (IZip zip : context.getZips(this.predicate::test)) {
            if (zip.isDir()) {
                //pass
            } else {
                log.info("file name:{} size:{}", zip.getName(), zip.getSize());
                zip.remove(removeStrategy);
            }
        }


    }
}
