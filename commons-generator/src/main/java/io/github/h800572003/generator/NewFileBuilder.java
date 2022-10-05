package io.github.h800572003.generator;

import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.new_code.INewFile;
import io.github.h800572003.generator.new_code.NewFile;

public class NewFileBuilder {

    private Protecteds protectedValue;

    private String name;

    private ICodeContext context;

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

    public INewFile addRootFile(INewFile newFile) {
        this.context.addNewFile(newFile);
        return newFile;
    }

    public NewFile build() {
        if (protectedValue == null) {
            throw new NewFileException("new class.protectedValue is necessary ");
        }
        if (name == null) {
            throw new NewFileException("new class.name is necessary ");
        }
        final NewFile newFile = new NewFile(this);
        return (NewFile) this.addRootFile(newFile);
    }

    public Protecteds getProtectedValue() {
        return protectedValue;
    }

    public String getName() {
        return name;
    }


}
