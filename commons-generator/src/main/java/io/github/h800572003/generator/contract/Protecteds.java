package io.github.h800572003.generator.contract;

public enum Protecteds {
    PRIVATE,
    PUBLIC,
    PROTECTED,


    VOID {
        @Override
        public String toJavaName() {
            return "";
        }
    };


    private String javaName;

    Protecteds() {
        this.javaName = name().toLowerCase();
    }

    public String toJavaName() {
        return this.javaName;
    }

    @Override
    public String toString() {
        return this.toJavaName();
    }
}
