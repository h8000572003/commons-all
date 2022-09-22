package io.github.h800572003.hcp;

public class HcpServices {

	public IHcpService getService(HcpOption ccpOption) {
		return new HcpService(ccpOption);
	}
}
