package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NewExceptions implements ICode {

    private List<NewException> exceptions = new ArrayList<>();

    @Override
    public String get() {
        return exceptions.isEmpty() ? //
                StringUtils.EMPTY : //
                "throws " + StringUtils.join(exceptions, ",");//

    }

    @Override
    public String toString() {
        return this.get();
    }

    public NewExceptions add(String throwable) {
        exceptions.add(new NewException(throwable));
        return this;
    }

    public static class NewException implements ICode {

        private String exceptionName;

        public NewException(String exceptionName) {
            this.exceptionName = exceptionName;
        }

        @Override
        public String get() {
            return exceptionName;
        }

        @Override
        public String toString() {
            return this.get();
        }
    }
}
