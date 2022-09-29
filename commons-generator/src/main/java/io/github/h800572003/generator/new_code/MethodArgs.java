package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Method args
 */
public class MethodArgs implements ICode {

    private List<MethodArg> methodArgs = new ArrayList<>();

    @Override
    public String get() {
        return StringUtils.join(methodArgs, ",");
    }

    public void add(MethodArg methodArg) {
        methodArgs.add(methodArg);
    }

    public static class MethodArg {
        private String name;
        private String typeName;

        public MethodArg(String typeName, String name) {
            this.name = name;
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName + " " + name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MethodArg methodArg = (MethodArg) o;
            return Objects.equals(name, methodArg.name) && Objects.equals(typeName, methodArg.typeName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, typeName);
        }
    }

    @Override
    public String toString() {
        return this.get();
    }
}
