package io.github.h800572003.hcp.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public enum HcpUtil {

	;
	/**
	 * 
	 * @param file
	 *            檔名
	 * @param path
	 *            相對路徑
	 * @return /path/file
	 */
	public static String toRestPath(String file, String... path) {
		return "/" + StringUtils.join(path, "/") + "/" + file;
	}

	public static String getMD5Value(String input) {
		String md5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}

			md5 = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.err.println("I'm sorry, but MD5 is not a valid message digest algorithm");
		}
		return md5;
	}

	public static String getBase64Value(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
	}
}
