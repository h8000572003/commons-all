package io.github.h800572003.hcp.method.put;

import io.github.h800572003.hcp.method.PathBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HcpPut implements IHcpPut {
	private String path;
	private final byte[] putByte;

	public HcpPut(PathBuilder pathBuilder, byte[] myByte) {
		super();
		this.path = pathBuilder.to();
		this.putByte = myByte;
	}

	public HcpPut(String path, File file) throws IOException {
		this(path, FileUtils.readFileToByteArray(file));
	}

	public HcpPut(String path, byte[] myByte) {
		super();
		this.path = path;
		this.putByte = myByte;
	}

	@Override
	public String toHcpPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public byte[] getPutByte() {
		return putByte;
	}
}
