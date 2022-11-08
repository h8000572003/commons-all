package io.github.h800572003.zip.upzip;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public interface IRemoveStrategy {
    void remove(ZipEntry zipEntry, ZipInputStream zipInputStream) throws IOException;
}
