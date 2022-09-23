package io.github.h800572003.hcp;

import io.github.h800572003.hcp.exception.HcpCodeException;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.IHcpMethod;
import io.github.h800572003.hcp.method.IStatusCodeChecker;
import io.github.h800572003.hcp.method.StatusCodeChecker;
import io.github.h800572003.hcp.method.delete.HcpApiDeleteMethod;
import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.GetHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiGetMethod;
import io.github.h800572003.hcp.method.get.HcpApiMethod;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.post.HcpApiPostMethod;
import io.github.h800572003.hcp.method.post.IHcpPost;
import io.github.h800572003.hcp.method.put.HcpApiPutMethod;
import io.github.h800572003.hcp.method.put.IHcpPut;
import io.github.h800572003.hcp.utils.HcpHttpClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.Serializable;

@Slf4j
public class HcpService implements IHcpService, IHcpContext {
	private final CloseableHttpClient client;
	private final IStatusCodeChecker statusCodeChecker;
	private final HcpOption option;
	private final HcpApiGetMethod hcpApiGetMethod = new HcpApiGetMethod();
	private final HcpApiDeleteMethod hcpApiDeleteMethod = new HcpApiDeleteMethod();
	private final HcpApiPutMethod hcpApiPutMethod = new HcpApiPutMethod();
	private final HcpApiPostMethod hcpApiPostMethod = new HcpApiPostMethod();
	private final IHcpMethodCheck hcpMethodCheck;

	public HcpService(CloseableHttpClient client, HcpOption option, IHcpMethodCheck hcpMethodCheck,
			IStatusCodeChecker statusCodeChecker) {
		this.client = client;
		this.option = option;
		this.option.check();
		this.hcpMethodCheck = hcpMethodCheck;
		this.statusCodeChecker = statusCodeChecker;
	}

	public HcpService(HcpOption option) {
		this(HcpHttpClientBuilder.build(), //
				option, //
				new HcpMethodCheck(), //
				new StatusCodeChecker());//
	}

	@Override
	public <O extends Serializable, I extends IHcpMethod> O execute(HcpApiMethod<I, O> api, I cmd)
			throws HcpCodeException {
		return api.execute(cmd, this);
	}

	@Override
	public GetHcpInfo execute(IHcpGet hcpGet) throws HcpCodeException {
		this.hcpMethodCheck.check(hcpGet);
		return this.execute(this.hcpApiGetMethod, hcpGet);
	}

	@Override
	public BaseHcpInfo execute(IHcpDelete hpDelete) throws HcpCodeException {
		this.hcpMethodCheck.check(hpDelete);
		return this.execute(this.hcpApiDeleteMethod, hpDelete);
	}

	@Override
	public BaseHcpInfo execute(IHcpPut hcpPut) throws HcpCodeException {
		this.hcpMethodCheck.check(hcpPut);
		return this.execute(this.hcpApiPutMethod, hcpPut);
	}

	@Override
	public HcpOption getOption() {
		return this.option;
	}

	@Override
	public CloseableHttpClient getClient() {
		return this.client;
	}

	@Override
	public void close() {
		try {
			this.client.close();
		} catch (final IOException e) {
			log.info("close error", e);
		}
	}

	@Override
	public String getAuthorization() {
		return option.getTokens().get();
	}

	@Override
	public IStatusCodeChecker getStatusCodeChecker() {
		return this.statusCodeChecker;
	}

	@Override
	public BaseHcpInfo execute(IHcpPost hcpPost) throws HcpCodeException {
		this.hcpMethodCheck.check(hcpPost);
		return this.execute(this.hcpApiPostMethod, hcpPost);
	}

}
