package io.github.h800572003.hcp.exception;

import java.text.MessageFormat;

public class HcpCodeException extends Exception {

	private static final long serialVersionUID = 1L;
	private final int code;

	public HcpCodeException(int code, String pattern, Object... arguments) {
		super(MessageFormat.format(pattern, arguments));
		this.code = code;
	}

	public HcpCodeException(int code, String string, Throwable throwable) {
		super(string, throwable);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
