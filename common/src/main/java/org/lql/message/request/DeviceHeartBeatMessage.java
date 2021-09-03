package org.lql.message.request;

import io.netty.buffer.ByteBuf;
import org.lql.message.Message;

/**
 * Title: DeviceHeartBeatMessage <br>
 * ProjectName: coldchain <br>
 * description: 心跳报文 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 14:03 <br>
 */
public class DeviceHeartBeatMessage extends RequesetMessage {
    private Byte cesq;

    @Override
    public void deserialize(ByteBuf byteBuf, Message message) {
        this.cesq = byteBuf.readByte();
    }

    public Byte getCesq() {
        return cesq;
    }

    public void setCesq(Byte cesq) {
        this.cesq = cesq;
    }

    @Override
    public String toString() {
        return "DeviceHeartBeatMessage{" +
                "cesq=" + cesq +
                "} " + super.toString();
    }
}
