package io.github.h800572003.hcp;

import io.github.h800572003.hcp.exception.HcpBusinessException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

@Getter
public class HcpOption {
	private final String rest;
	private final Supplier<String> tokens;
	public HcpOption(String rest, Supplier<String> tokens) {
		super();
		this.rest = rest;
		this.tokens = tokens;
	}
	public void check() {
		if (tokens == null) {
			throw new HcpBusinessException("tokens is require");
		}
		if (StringUtils.isBlank(rest)) {
			throw new HcpBusinessException("rest is require");
		}
	}

	
}
