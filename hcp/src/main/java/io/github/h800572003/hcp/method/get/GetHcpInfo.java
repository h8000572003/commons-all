package io.github.h800572003.hcp.method.get;

import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GetHcpInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CloseableHttpResponse response;
	private final BaseHcpInfo baseHcpInfo;

	public GetHcpInfo(CloseableHttpResponse closeableHttpResponse) {
		super();
		this.response = closeableHttpResponse;
		this.baseHcpInfo = new BaseHcpInfo(closeableHttpResponse);
	}
	public String getSize() {
		return baseHcpInfo.getValue("X-HCP-Size", "0");
	}
	public void download(File file) {
		try {
			final HttpEntity entity = this.response.getEntity();
			FileUtils.copyInputStreamToFile(entity.getContent(), file);
		} catch (UnsupportedOperationException | IOException e) {
			throw new HcpBusinessException("download error", e);
		}
	}

}
