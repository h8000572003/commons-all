package io.github.h800572003.hcp;

import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.post.IHcpPost;
import io.github.h800572003.hcp.method.put.IHcpPut;

public interface IHcpMethodCheck {
	void check(IHcpGet api);

	void check(IHcpDelete api);

	void check(IHcpPut api);

	void check(IHcpPost api);

}