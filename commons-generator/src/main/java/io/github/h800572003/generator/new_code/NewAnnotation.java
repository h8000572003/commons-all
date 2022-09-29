package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;

public class NewAnnotation implements ICode {

    private final NewStringLine stringLine;

    public NewAnnotation(String text) {
        this.stringLine = new NewStringLine(text,false);
    }

    @Override
    public String get() {
        return  this.stringLine.get();
    }

    @Override
    public String toString() {
        return this.get();
    }
}
