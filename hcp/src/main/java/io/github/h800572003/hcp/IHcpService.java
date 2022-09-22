package io.github.h800572003.hcp;

import io.github.h800572003.hcp.exception.HcpCodeException;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.IHcpMethod;
import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.GetHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiMethod;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.post.IHcpPost;
import io.github.h800572003.hcp.method.put.IHcpPut;

import java.io.Serializable;

public interface IHcpService {

	<O extends Serializable, I extends IHcpMethod> O execute(HcpApiMethod<I, O> api, I cmd) throws HcpCodeException;

	GetHcpInfo execute(IHcpGet hcpGet) throws HcpCodeException;

	BaseHcpInfo execute(IHcpPost hcpPost) throws HcpCodeException;

	BaseHcpInfo execute(IHcpDelete hpDelete) throws HcpCodeException;

	BaseHcpInfo execute(IHcpPut hcpPut) throws HcpCodeException;

	void close();

}