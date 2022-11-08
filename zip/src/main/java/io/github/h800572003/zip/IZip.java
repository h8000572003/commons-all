package io.github.h800572003.zip;

import io.github.h800572003.zip.upzip.IRemoveStrategy;
import io.github.h800572003.zip.upzip.UnZipService;

import java.io.IOException;

public interface IZip {
    boolean isDir();


    /**
     * 檔案名稱
     *
     * @return
     */
    String getName();

    void remove(IRemoveStrategy removeStrategy) throws IOException;


    Long getSize();
}
