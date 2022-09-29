package io.github.h800572003.generator;

/**
 * 新增檔案異常
 */
public  class NewFileException extends RuntimeException {

    public NewFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
