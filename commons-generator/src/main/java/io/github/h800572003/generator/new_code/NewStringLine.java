package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;

public class NewStringLine implements ICode {

    private String body;
    private boolean withEndNote = true;

    public NewStringLine(String body, boolean withEndNote) {
        this.body = body;
        this.withEndNote = withEndNote;
    }
    public NewStringLine(String body){
        this(body,true);
    }

    @Override
    public String get() {
        return body + (withEndNote ? ";" : "");
    }

    @Override
    public String toString() {
        return this.get();
    }


}
