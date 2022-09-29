package io.github.h800572003.generator;

import io.github.h800572003.generator.new_code.INewFile;
import io.github.h800572003.generator.new_code.NewFile;

import java.util.List;

/**
 * 產生內容
 * @param <T>
 */
public interface ICodeContext {
    NewFileBuilder createNewFile();

    /**
     * 加入newFile
     * @param newFile
     */
    void addNewFile( INewFile newFile);


    List<INewFile> getNewFiles();


}
