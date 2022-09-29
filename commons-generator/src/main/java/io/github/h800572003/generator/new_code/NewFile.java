package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.NewFileGenerator;

/**
 * 建立新檔案
 */
public class NewFile {

    private NewClass newClass;


    public NewFile(NewFileGenerator.NewFileBuilder newFileBuilder) {
        this.newClass = new NewClass(newFileBuilder.protectedValue, newFileBuilder.name);

    }


    public NewClass getNewClass() {
        return newClass;
    }

    @Override
    public String toString() {
        return newClass.get();
    }
}
