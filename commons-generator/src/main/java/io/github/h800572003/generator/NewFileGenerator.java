package io.github.h800572003.generator;

import io.github.h800572003.generator.new_code.INewFile;
import io.github.h800572003.generator.new_code.NewFile;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 新檔案產生器
 */
@Slf4j
public class NewFileGenerator implements ICodeContext, INewFileGenerator {
    /**
     * 檔案產製
     */
    public interface IFileGeneratorOutput {
        void export(NewFileGenerator newFileGenerator);
    }

    /**
     * 程式碼產製
     */
    public interface ICodeGenerator {
        void generator(ICodeContext codeContext);

    }

    private final ICodeGenerator generator;//產生器
    private final List<INewFile> newFiles = new ArrayList<>();//產製檔案
    private final IFileGeneratorOutput output;//輸出

    public NewFileGenerator(ICodeGenerator generator, IFileGeneratorOutput output) {
        this.generator = generator;
        this.output = output;
    }

    @Override
    public List<INewFile> getNewFiles() {
        return this.newFiles;
    }


    @Override
    public NewFileBuilder createNewFile() {
        return new NewFileBuilder(this);
    }
    @Override
    public void addNewFile(INewFile newFile) {
        this.newFiles.add(newFile);
    }

    @Override
    public void export() {
        this.generator.generator(this);
        this.output.export(this);
    }


}
