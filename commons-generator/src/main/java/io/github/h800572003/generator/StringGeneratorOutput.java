package io.github.h800572003.generator;

import io.github.h800572003.generator.new_code.INewFile;

import java.io.StringWriter;

/**
 * 文字產出
 */

public class StringGeneratorOutput implements IFileGeneratorOutput {

    private StringWriter writer;

    public StringGeneratorOutput(StringWriter writer) {
        this.writer = writer;
    }
    public StringGeneratorOutput() {
       this(new StringWriter());
    }

    @Override
    public void export(NewFileGenerator newFileGenerator) {
        newFileGenerator.getNewFiles().forEach(this::newCode);
    }

    private void newCode(INewFile file) {
        writer.write(file.toString());
    }


    @Override
    public String toString() {
        return writer.toString();
    }
}
