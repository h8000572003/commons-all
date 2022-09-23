package io.github.h800572003.hcp;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.h800572003.hcp.exception.HcpCodeException;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.PathBuilder;
import io.github.h800572003.hcp.method.delete.HcpDelete;
import io.github.h800572003.hcp.method.get.GetHcpInfo;
import io.github.h800572003.hcp.method.get.HcpGet;
import io.github.h800572003.hcp.method.put.HcpPut;
import io.github.h800572003.hcp.utils.HcpUtil;
import lombok.extern.slf4j.Slf4j;

@Disabled
@Slf4j
class HcpServiceTest {

	IHcpService hcpService;
	private String token = "";
	private final String user = "-";
	private final String pwd = "-";
	private final String url = "-";

	public HcpServiceTest() {
		token = "HCP " + HcpUtil.getBase64Value(user) + ":" + HcpUtil.getMD5Value(pwd);
	}

	@BeforeEach
	public void init() {

		HcpOption option = new HcpOption(url, () -> token);
		this.hcpService = new HcpService(option);
	}

	@Test
	void test_execute_then_get_then_size() throws HcpCodeException {

		PathBuilder path = PathBuilder.builder()//
				.filename("x.pdf")//
				.path("1")///
				.path("2")//
				.path("3");//

		HcpGet hcpDownloadInfo = new HcpGet("/tax");

		GetHcpInfo execute = hcpService.execute(hcpDownloadInfo);
		log.info("execute:{}", execute);
		// String size = execute.getSize();

	}

	@Test
	void test_execute_then_delete() throws HcpCodeException {

		PathBuilder path = PathBuilder.builder()//
				.filename("x.pdf")//
				.path("1")///
				.path("2")//
				.path("3");//

		HcpDelete cmd = new HcpDelete(path);

		BaseHcpInfo execute = hcpService.execute(cmd);

	}

	@Test
	void test_execute_then_put() throws IOException, URISyntaxException, HcpCodeException {

		PathBuilder path = PathBuilder.builder()//
				.filename("x.pdf")//
				.path("1")///
				.path("2")//
				.path("3");//

		URL resource = HcpServiceTest.class.getClassLoader().getResource("test.zip");

		assert resource != null;
		byte[] myByte = FileUtils.readFileToByteArray(new File(resource.toURI()));

		HcpPut cmd = new HcpPut(path, myByte);

		BaseHcpInfo execute = hcpService.execute(cmd);

	}

	@AfterEach
	public void close() {
		hcpService.close();
	}

}
