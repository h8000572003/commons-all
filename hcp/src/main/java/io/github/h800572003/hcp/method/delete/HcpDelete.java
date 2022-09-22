package io.github.h800572003.hcp.method.delete;

import io.github.h800572003.hcp.method.PathBuilder;

public class HcpDelete implements IHcpDelete {
	private final String path;

	public HcpDelete(PathBuilder pathBuilder) {
		super();
		this.path = pathBuilder.to();
	}

	public HcpDelete(String path) {
		super();
		this.path = path;
	}

	@Override
	public String toHcpPath() {
		return path;
	}

}
