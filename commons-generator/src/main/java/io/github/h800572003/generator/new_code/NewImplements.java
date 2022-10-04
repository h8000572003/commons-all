package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class NewImplements implements ICode {

    private List<NewImplement> newImplements = new ArrayList<>();

    public NewImplements() {

    }

    @Override
    public String get() {
        return CollectionUtils.isEmpty(newImplements) ? "" : "implements "+StringUtils.join(newImplements, ",");
    }

    @Override
    public String toString() {
        return this.get();
    }

    public NewImplements add(String implement) {
        this.newImplements.add(new NewImplement(implement));
        return this;
    }

    public static class NewImplement implements ICode {

        private NewStringLine stringLine;

        public NewImplement(String implement) {
            this.stringLine = new NewStringLine(implement, false);
        }

        @Override
        public String get() {
            return this.stringLine.get();
        }

        @Override
        public String toString() {
            return this.get();
        }
    }
}
