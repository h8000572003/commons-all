package io.github.h800572003.hcp.method;

import io.github.h800572003.hcp.utils.HcpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Path builder
 */
public class PathBuilder {
	private String fileName;
	private final List<String> folders = new ArrayList<>();

	public static PathBuilder builder() {
		return new PathBuilder();

	}

	public PathBuilder filename(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public PathBuilder path(String path) {
		this.folders.add(path);
		return this;
	}

	public String to() {
		return HcpUtil.toRestPath(fileName, folders.toArray(new String[] {}));
	}

}
