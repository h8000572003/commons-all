package io.github.h800572003.hcp.method.get;

import io.github.h800572003.hcp.HcpOption;
import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.exception.HcpCodeException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * 使用GET 方法
 */
public class HcpApiGetMethod implements HcpApiMethod<IHcpGet, GetHcpInfo> {

    @Override
    public GetHcpInfo execute(IHcpGet get, IHcpContext hcpService) throws HcpCodeException {
        HcpOption option = hcpService.getOption();
        CloseableHttpClient client = hcpService.getClient();
        HttpGet request = new HttpGet(option.getRest() + get.toHcpPath());
        request.addHeader("Authorization", hcpService.getAuthorization());
        try (CloseableHttpResponse response = client.execute(request)) {
            final int statusCode = response.getStatusLine().getStatusCode();
            if (hcpService.getStatusCodeChecker().isOk(get, statusCode)) {
                return new GetHcpInfo(response);
            }
            throw new HcpCodeException(statusCode, response.getStatusLine().getReasonPhrase());
        } catch (ClientProtocolException e) {
            throw new HcpBusinessException("HcpDownloadInfo error ClientProtocolException", e);
        } catch (IOException e) {
            throw new HcpBusinessException("HcpDownloadInfo error IOException", e);
        }
    }

}
