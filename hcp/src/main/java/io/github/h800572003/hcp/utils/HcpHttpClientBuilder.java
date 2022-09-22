package io.github.h800572003.hcp.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
@Slf4j
public class HcpHttpClientBuilder {

	public static CloseableHttpClient build() {
		try {

			SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL)
					.loadTrustMaterial((x, y) -> true).build();

			return HttpClientBuilder.create().setSslcontext(sslContext)//
					.setSSLHostnameVerifier(new NoopHostnameVerifier())//
					.setConnectionTimeToLive(70, TimeUnit.SECONDS)//
					.setMaxConnTotal(100).build();
		}

		catch (Exception e) {
			log.error("get CloseableHttpClient ", e);
			return null;
		}
	}

}
