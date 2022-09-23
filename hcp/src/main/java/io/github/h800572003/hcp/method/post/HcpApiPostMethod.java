package io.github.h800572003.hcp.method.post;

import io.github.h800572003.hcp.HcpOption;
import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.exception.HcpCodeException;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiMethod;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * 使用Post
 */
public class HcpApiPostMethod implements HcpApiMethod<IHcpPost, BaseHcpInfo> {

	
	@Override
	public BaseHcpInfo execute(IHcpPost hcpMethod, IHcpContext hcpService) throws HcpCodeException {
		HcpOption option = hcpService.getOption();
		CloseableHttpClient client = hcpService.getClient();
		HttpPost request = new HttpPost(option.getRest() + hcpMethod.toHcpPath());
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
