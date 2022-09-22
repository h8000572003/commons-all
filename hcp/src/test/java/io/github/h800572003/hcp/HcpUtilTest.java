package io.github.h800572003.hcp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.h800572003.hcp.utils.HcpUtil;

class HcpUtilTest {

	@Test
	void test_base64Value() {
		String base64Value = HcpUtil.getBase64Value("ABC");
		assertThat(base64Value).isEqualTo("QUJD");

	}

	@Test
	void test_getMD5Value() {
		String value = HcpUtil.getMD5Value("ABC");
		assertThat(value).isEqualTo("902fbdd2b1df0c4f70b4a5d23525e932");

	}
}
