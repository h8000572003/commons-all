package io.github.h800572003.hcp.exception;

import java.text.MessageFormat;

public class HcpBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HcpBusinessException(String pattern, Object... arguments) {
		super(MessageFormat.format(pattern, arguments));
	}

	public HcpBusinessException(String string, Throwable throwable) {
		super(string, throwable);
	}
}
