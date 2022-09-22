package io.github.h800572003.hcp.method;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class BaseHcpInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Map<String, String> values = new ConcurrentHashMap<>();

	public BaseHcpInfo(CloseableHttpResponse closeableHttpResponse) {
		super();
		this.init(closeableHttpResponse);
	}

	private void init(CloseableHttpResponse response) {
		Stream.of(response.getAllHeaders())//
				.forEach(i -> this.values.put(i.getName(), i.getValue()));//

	}

	public String getValue(String key, String def) {
		return this.values.getOrDefault(key, def);
	}
}
