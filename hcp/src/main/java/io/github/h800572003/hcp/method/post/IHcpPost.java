package io.github.h800572003.hcp.method.post;

import io.github.h800572003.hcp.method.IHcpMethod;

public interface IHcpPost extends IHcpMethod {

	String toHcpPath();

	byte[] getPutByte();
}
