package io.github.h800572003.hcp;

import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.post.IHcpPost;
import io.github.h800572003.hcp.method.put.IHcpPut;
import org.apache.commons.lang3.StringUtils;

public class HcpMethodCheck implements IHcpMethodCheck {

	private static final String PUT_BYTE_IS_REQUIRE = "putByte is require";
	private static final String PATH_IS_REQUIRE = "path is require";

	@Override
	public void check(IHcpGet api) {
		if (StringUtils.isBlank(api.toHcpPath())) {
			throw new HcpBusinessException(PATH_IS_REQUIRE);
		}
	}

	@Override
	public void check(IHcpDelete api) {
		if (StringUtils.isBlank(api.toHcpPath())) {
			throw new HcpBusinessException(PATH_IS_REQUIRE);
		}
	}

	@Override
	public void check(IHcpPut api) {
		if (StringUtils.isBlank(api.toHcpPath())) {
			throw new HcpBusinessException(PATH_IS_REQUIRE);
		}
		if (api.getPutByte() == null) {
			throw new HcpBusinessException(PUT_BYTE_IS_REQUIRE);
		}
	}

	@Override
	public void check(IHcpPost api) {
		if (StringUtils.isBlank(api.toHcpPath())) {
			throw new HcpBusinessException(PATH_IS_REQUIRE);
		}
		if (api.getPutByte() == null) {
			throw new HcpBusinessException(PUT_BYTE_IS_REQUIRE);
		}
		
	}

}
