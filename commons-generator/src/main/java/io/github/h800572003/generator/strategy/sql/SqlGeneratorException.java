package io.github.h800572003.generator.strategy.sql;

public class SqlGeneratorException extends RuntimeException {

    public SqlGeneratorException(String message) {
        super(message);
    }

    public SqlGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
