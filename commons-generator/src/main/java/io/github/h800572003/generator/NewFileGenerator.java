package io.github.h800572003.generator;

import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.new_code.NewClass;
import io.github.h800572003.generator.new_code.NewFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NewFileGenerator implements ICodeContext, INewFileGenerator {


    public interface CodeGenerator {
        void generator(ICodeContext codeContext);
    }

    private CodeGenerator generator;


    private List<NewFile> newFiles = new ArrayList<>();


    private IFileGeneratorOutput output;


    @Override
    public List<NewFile> getNewFiles() {
        return this.newFiles;
    }

    public NewFileGenerator(CodeGenerator generator, IFileGeneratorOutput output) {
        this.generator = generator;
        this.output = output;
    }

    static public class FileGeneratorOutput implements IFileGeneratorOutput {

        private final String base;

        public FileGeneratorOutput(String base) {
            this.base = base;
        }

        @Override
        public void export(NewFileGenerator newFileGenerator) {
            List<NewFile> newFiles = newFileGenerator.getNewFiles();
            for (NewFile file : newFiles) {
                NewClass newClass = file.getNewClass();
                final String name = newClass.getName();
                try (FileWriter writer = new FileWriter(new File(base, name + ".java"), false)) {
                    IOUtils.write(file.toString(), writer);
                } catch (IOException e) {
                    throw new NewFileException("file:" + name, e);
                }
            }

        }
    }


    @Override
    public NewFileBuilder createNewFile() {
        return new NewFileBuilder(this);
    }

    @Override
    public void addNewFile(NewFile newFile) {
        this.newFiles.add(newFile);
    }

    public static class NewFileBuilder {

        public Protecteds protectedValue;
        public String name;
        public ICodeContext context;

        public NewFileBuilder(ICodeContext context) {
            this.context = context;
        }

        public NewFileBuilder setProtectedValue(Protecteds protectedValue) {
            this.protectedValue = protectedValue;
            return this;
        }

        public NewFileBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public NewFile build() {
            NewFile newFile = new NewFile(this);
            this.context.addNewFile(newFile);
            return newFile;
        }
    }

    public interface IFileGeneratorOutput {
        void export(NewFileGenerator newFileGenerator);
    }

    @Override
    public void export() {
        this.generator.generator(this);
        this.output.export(this);
    }


}
