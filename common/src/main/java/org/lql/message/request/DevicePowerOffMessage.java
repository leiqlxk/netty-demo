package org.lql.message.request;

import io.netty.buffer.ByteBuf;
import org.lql.message.Message;

/**
 * Title: DevicePowerOffMessage <br>
 * ProjectName: coldchain <br>
 * description: 设备关机 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 14:07 <br>
 */
public class DevicePowerOffMessage extends RequesetMessage {

    // 2021年，只写21年
    private Byte year;

    private Byte month;

    private Byte day;

    private Byte hour;

    private Byte minute;

    private Byte second;

    @Override
    public void deserialize(ByteBuf byteBuf, Message message) {
        this.year = byteBuf.readByte();
        this.month = byteBuf.readByte();
        this.day = byteBuf.readByte();
        this.hour = byteBuf.readByte();
        this.minute = byteBuf.readByte();
        this.second = byteBuf.readByte();
    }

    @Override
    public String toString() {
        return "DevicePowerOffMessage{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                "} " + super.toString();
    }
}
