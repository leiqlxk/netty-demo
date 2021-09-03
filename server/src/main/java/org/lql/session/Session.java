package org.lql.session;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * Title: Session <br>
 * ProjectName: coldchain <br>
 * description: 会话管理接口 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 17:25 <br>
 */
public interface Session {
    /**
     * description: 绑定会话 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:52 <br>
     *
     * @throws
     * @param channel  要绑定会话的channel
     * @param deviceId 会话绑定设备id
     * @return void
     */
    void bind(Channel channel, String deviceId);

    /**
     * description: 解绑会话 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:52 <br>
     *
     * @throws
     * @param channel 要解绑的channel
     * @return void
     */
    void unbind(Channel channel);

    /**
     * description: 获取属性 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:51 <br>
     *
     * @throws
     * @param channel 要获取的channel
     * @param keyName 属性键名
     * @return java.lang.Object
     */
    Object getAttribute(Channel channel, AttributeKey<String> keyName);


    /**
     * description: 设置属性 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:50 <br>
     *
     * @throws
     * @param channel 要设置属性的channel
     * @param keyName 属性键名
     * @param value 值
     * @return void
     */
    void  setAttribute(Channel channel, AttributeKey<String> keyName, String value);

    /**
     * description: 判断一个通道是否有设备在使用 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:59 <br>
     *
     * @throws
     * @param channel
     * @return boolean
     */
    boolean hasDevice(Channel channel);

    /**
     * description: 根据设备id获取通道 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:59 <br>
     *
     * @throws
     * @param deviceId 设备id
     * @return io.netty.channel.Channel
     */
    Channel getChannelByDeviceId(String deviceId);
}
