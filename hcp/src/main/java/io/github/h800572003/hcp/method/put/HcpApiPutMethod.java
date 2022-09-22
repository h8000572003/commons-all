package io.github.h800572003.hcp.method.put;

import io.github.h800572003.hcp.HcpOption;
import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.exception.HcpCodeException;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiMethod;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * 使用put 方法
 */
public class HcpApiPutMethod implements HcpApiMethod<IHcpPut, BaseHcpInfo> {

	@Override
	public BaseHcpInfo execute(IHcpPut hcpMethod, IHcpContext hcpService) throws HcpCodeException {
		HcpOption option = hcpService.getOption();
		CloseableHttpClient client = hcpService.getClient();
		HttpPut request = new HttpPut(option.getRest() + hcpMethod.toHcpPath());
		request.addHeader("Authorization", hcpService.getAuthorization());
		ByteArrayEntity requestEntity = new ByteArrayEntity(hcpMethod.getPutByte());
		request.setEntity(requestEntity);

		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (hcpService.getStatusCodeChecker().isOk(hcpMethod, statusCode)) {
				return new BaseHcpInfo(response);
			}
			throw new HcpCodeException(statusCode, response.getStatusLine().getReasonPhrase());
		} catch (ClientProtocolException e) {
			throw new HcpBusinessException("HcpApiDeleteMethod error ClientProtocolException", e);
		} catch (IOException e) {
			throw new HcpBusinessException("HcpApiDeleteMethod error IOException", e);
		}
	}

}
