package io.github.h800572003.hcp.method.put;

import io.github.h800572003.hcp.method.IHcpMethod;

public interface IHcpPut extends IHcpMethod {

	String toHcpPath();

	byte[] getPutByte();
}
