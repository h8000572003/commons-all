package io.github.h800572003.zip.upzip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 一般輸出方式
 */
public class CommonRemoveStrategy implements IRemoveStrategy {
    Path path;

    public CommonRemoveStrategy(Path path) {
        this.path = path;
    }

    @Override
    public void remove(ZipEntry zipEntry, ZipInputStream zipInputStream) throws IOException {
        byte[] buffer = new byte[1024];
        try (FileOutputStream fos = new FileOutputStream(new File(path.toFile(), zipEntry.getName()))) {
            int len;
            while ((len = zipInputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }


}
