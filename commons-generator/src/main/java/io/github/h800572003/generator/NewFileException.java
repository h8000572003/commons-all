package io.github.h800572003.generator;

/**
 * 新增檔案異常
 */
public  class NewFileException extends RuntimeException {

    public NewFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewFileException(String message) {
        super(message);
    }

    public NewFileException() {
        super();
    }

    public NewFileException(Throwable cause) {
        super(cause);
    }

    protected NewFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
