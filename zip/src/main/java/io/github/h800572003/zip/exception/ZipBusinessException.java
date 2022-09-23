package io.github.h800572003.zip.exception;

import java.text.MessageFormat;

public class ZipBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ZipBusinessException(String pattern, Object... arguments) {
		super(MessageFormat.format(pattern, arguments));
	}

	public ZipBusinessException(String string, Throwable throwable) {
		super(string, throwable);
	}
}
