package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.NewFileBuilder;
import io.github.h800572003.generator.utils.CoderUtils;

/**
 * 建立新檔案
 */
public class NewFile implements INewFile {

    public static final String JAVA = ".java";
    private final NewClass newClass;


    public NewFile(NewFileBuilder newFileBuilder) {
        this.newClass = new NewClass(newFileBuilder.getProtectedValue(), newFileBuilder.getName());
    }


    public NewClass getNewClass() {
        return newClass;
    }

    @Override
    public String toString() {
        return newClass.get();
    }

    @Override
    public String getPath() {
        return CoderUtils.getFilePath(newClass.getPackageCode().getPackageString());
    }

    @Override
    public String getFileName() {
        return newClass.getName() + JAVA;
    }

    @Override
    public String get() {
        return toString();
    }
}
