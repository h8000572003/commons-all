package io.github.h800572003.event.deiven;

import com.google.common.collect.Maps;
import io.github.h800572003.exception.ApBusinessException;

import java.util.Map;

public class DynamicRouter implements IDynamicRouter<IMessage> {

	@SuppressWarnings("rawtypes")
	private Map<Class<? extends IMessage>, IChannel> map = Maps.newConcurrentMap();

	@Override
	public void registerChannel(Class<? extends IMessage> messageType, IChannel<? extends IMessage> channel) {
		map.put(messageType, channel);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T2 extends IMessage> void dispatch(T2 message) {
		if (this.map.containsKey(message.getClass())) {
			this.map.get(message.getClass()).dispatch(message);
		} else {
			throw new ApBusinessException("not match channel:{0}", message);
		}

	}

}
