package io.github.h800572003.generator;

import io.github.h800572003.generator.new_code.NewFile;

import java.util.List;

/**
 * 產生內容
 * @param <T>
 */
public interface ICodeContext<T extends ICode> {
    NewFileGenerator.NewFileBuilder createNewFile();

    /**
     * 加入newFile
     * @param newFile
     */
    void addNewFile( NewFile newFile);





    List<NewFile> getNewFiles();


}
