package org.lql.message.request;

import io.netty.buffer.ByteBuf;
import org.lql.message.Message;

import java.nio.charset.Charset;

/**
 * Title: DeviceNewConnectMessage <br>
 * ProjectName: coldchain <br>
 * description: 设备新建立连接 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 14:02 <br>
 */
public class DeviceNewConnectMessage extends RequesetMessage {

    // 设备类型
    private Byte model;

    // 设备号
    private String deviceId;

    private String iccid;


    @Override
    public void deserialize(ByteBuf byteBuf, Message message) {
        this.model = byteBuf.readByte();
        this.deviceId = byteBuf.readCharSequence(16, Charset.defaultCharset()).toString().trim();
        this.iccid = byteBuf.readCharSequence(16, Charset.defaultCharset()).toString().trim();
//        this.iccid = byteBuf.readCharSequence(20, Charset.defaultCharset()).toString().trim();
    }

    public Byte getModel() {
        return model;
    }

    public void setModel(Byte model) {
        this.model = model;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    @Override
    public String toString() {
        return "DeviceNewConnectMessage{" +
                "model=" + model +
                ", deviceId='" + deviceId + '\'' +
                ", iccid='" + iccid + '\'' +
                "} " + super.toString();
    }
}
