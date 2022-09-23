package io.github.h800572003.telegram;

import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

public class BotMedidata {

	private String token;
	private String username;

	private Supplier<List<String>> supplierUserIds;

	public Supplier<List<String>> getSupplierUserIds() {
		return supplierUserIds;
	}

	public void setSupplierUserIds(Supplier<List<String>> supplierUserIds) {
		this.supplierUserIds = supplierUserIds;
	}

	public boolean isUseBot() {
		return StringUtils.isNotBlank(this.token) && StringUtils.isNotBlank(this.username);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
