package io.github.h800572003.hcp;

import io.github.h800572003.hcp.method.IStatusCodeChecker;
import org.apache.http.impl.client.CloseableHttpClient;

public interface IHcpContext {

	HcpOption getOption();

	CloseableHttpClient getClient();

	String getAuthorization();

	IStatusCodeChecker getStatusCodeChecker();

}