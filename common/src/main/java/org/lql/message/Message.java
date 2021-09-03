package org.lql.message;


import org.lql.constant.ColdConstants;
import org.lql.message.request.DeviceHeartBeatMessage;
import org.lql.message.request.DeviceNewConnectMessage;
import org.lql.message.request.DevicePowerOffMessage;
import org.lql.message.request.DevicePowerOnMessage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: Message <br>
 * ProjectName: coldchain <br>
 * description: 消息基类 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 11:41 <br>
 */
public abstract class Message implements Serializable {

    // 开始标志
    private Integer startFlag;

    // 长度
    private Integer length;

    // 序号
    private Byte Sn;

    // 报文类型
    private Byte pktType;


    private static final Map<Integer, Class<? extends Message>> messageClasses = new HashMap<>();

    public Message() {
    }

    public Message(Integer startFlag, Integer length, Byte sn, Byte pktType) {
        this.startFlag = startFlag;
        this.length = length;
        Sn = sn;
        this.pktType = pktType;
    }

    /**
     * 根据报文类型字节，获得对应的消息 class
     * @param reportType 消息类型字节
     * @return 消息 class
     */
    public static Class<? extends Message> getMessageClass(int reportType) {
        return messageClasses.get(reportType);
    }



    static {
        messageClasses.put(ColdConstants.DEVICE_POWER_ON, DevicePowerOnMessage.class);
        messageClasses.put(ColdConstants.DEVICE_NEW_CONNECT, DeviceNewConnectMessage.class);
        messageClasses.put(ColdConstants.DEVICE_HEART_BEAT, DeviceHeartBeatMessage.class);
        messageClasses.put(ColdConstants.DEVICE_POWER_OFF, DevicePowerOffMessage.class);
    }

    public Integer getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(Integer startFlag) {
        this.startFlag = startFlag;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Byte getSn() {
        return Sn;
    }

    public void setSn(Byte sn) {
        Sn = sn;
    }

    public Byte getPktType() {
        return pktType;
    }

    public void setPktType(Byte pktType) {
        this.pktType = pktType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "startFlag=" + startFlag +
                ", length=" + length +
                ", Sn=" + Sn +
                ", pktType=" + pktType +
                '}';
    }
}
