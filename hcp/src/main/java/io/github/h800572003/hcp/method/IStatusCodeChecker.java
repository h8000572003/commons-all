package io.github.h800572003.hcp.method;

public interface IStatusCodeChecker {

	/**
	 * 是否ok
	 *
	 */
	boolean isOk(IHcpMethod hcpMethod, int code);

}
