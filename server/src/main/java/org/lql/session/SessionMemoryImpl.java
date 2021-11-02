package org.lql.session;


import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.lql.utils.AttributeKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: SessionMemoryImpl <br>
 * ProjectName: coldchain <br>
 * description: 会话管理实现类 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 17:28 <br>
 */
public class SessionMemoryImpl implements Session {
    private static final Logger log = LoggerFactory.getLogger(SessionMemoryImpl.class);

    private final Map<String, Channel> deviceChannelMap = new ConcurrentHashMap<>();

    @Override
    public int bind(Channel channel, String deviceId) {
        int flag = 0;
        channel.attr(AttributeKeyUtils.getDeviceid()).set(deviceId);
        Channel ch = deviceChannelMap.putIfAbsent(deviceId, channel);

        if (ch == null) {
            flag = 1;
        }

        if (ch != null && ch != channel) {
            log.error("===========================设备[{}]已新建连接[{}]，断开旧连接：[{}]", deviceId, ch.id(), channel.id());
            ch.close();
            deviceChannelMap.put(deviceId, channel);
            flag = 1;
        }
        return flag;
    }

    @Override
    public void unbind(Channel channel) {
        if (hasDevice(channel)) {
            deviceChannelMap.remove(channel.attr(AttributeKeyUtils.getDeviceid()).get(), channel);
        }
    }


    @Override
    public Object getAttribute(Channel channel, AttributeKey<String> keyName) {
        return channel.attr(keyName).get();
    }

    @Override
    public void setAttribute(Channel channel, AttributeKey<String> keyName, String value) {
        channel.attr(keyName).set(value);
    }

    @Override
    public boolean hasDevice(Channel channel) {
        AttributeKey<String> key = AttributeKeyUtils.getDeviceid();
        return (channel.hasAttr(key) || channel.attr(key).get() != null);
    }


    @Override
    public Channel getChannelByDeviceId(String deviceId) {
        return deviceChannelMap.get(deviceId);
    }
}
