package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;

import java.util.Objects;

public class Imports implements ICode {
    private final GroupCode groupCode = new GroupCode();

    public void addImport(Class<?> plass) {
        this.addImport(plass.getName());
    }

    public Imports addImport(String pClass) {
        groupCode.add(new ImportClass(pClass));
        return this;
    }

    @Override
    public String get() {
        return groupCode.get();
    }

    @Override
    public String toString() {
        return get();
    }

    public static class ImportClass implements ICode {
        private final String importString;

        public ImportClass(String importString) {
            this.importString = importString;
        }

        @Override
        public String get() {
            return "import " + importString + ";";
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ImportClass that = (ImportClass) o;
            return Objects.equals(importString, that.importString);
        }

        @Override
        public int hashCode() {
            return Objects.hash(importString);
        }

        @Override
        public String toString() {
            return this.get();
        }
    }
}
