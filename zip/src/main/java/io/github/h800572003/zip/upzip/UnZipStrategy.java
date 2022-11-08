package io.github.h800572003.zip.upzip;

import java.io.IOException;

@FunctionalInterface
public
interface UnZipStrategy {
    void handle(IUnZipContext context) throws IOException;
}
