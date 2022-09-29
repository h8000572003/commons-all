package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;

/**
 * 新增檔案介面
 */
public interface INewFile extends ICode {

    /**
     * 路徑
     * @return
     */
     String getPath();

    /**
     * 檔名
     * @return
     */
    String getFileName();
}
