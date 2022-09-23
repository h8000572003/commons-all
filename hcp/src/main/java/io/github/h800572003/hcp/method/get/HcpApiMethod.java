package io.github.h800572003.hcp.method.get;

import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpCodeException;
import io.github.h800572003.hcp.method.IHcpMethod;

import java.io.Serializable;

/**
 * HCP API METHOD
 * @param <I>
 * @param <O>
 */
public interface HcpApiMethod<I extends IHcpMethod, O extends Serializable> {
	/**
	 * 執行
	 * @param hcpMethod 呼叫方法
	 * @param hcpService hcp service 上下文
	 */
	O execute(I hcpMethod, IHcpContext hcpService) throws HcpCodeException;

}
