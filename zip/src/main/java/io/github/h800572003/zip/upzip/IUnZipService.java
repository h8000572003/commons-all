package io.github.h800572003.zip.upzip;

import java.io.InputStream;

/**
 * 解壓縮服務策略
 */
public interface IUnZipService {


    void execute(InputStream inputStream, UnZipStrategy unZipStrategy);


}
