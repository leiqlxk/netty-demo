package org.lql.constant;

/**
 * Title: ClodConstants <br>
 * ProjectName: coldchain <br>
 * description: 常量类 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 10:10 <br>
 */
public interface ColdConstants {

    // 报文类型
    // 设备开机
    int DEVICE_POWER_ON = 0;
    // 设备新建立连接
    int DEVICE_NEW_CONNECT = 7;
    // 响应报文
    int PACKET_ACK = 8;
    // 心跳报文
    int DEVICE_HEART_BEAT = 10;
    // 设备关机
    int DEVICE_POWER_OFF = 21;

    // 其实标志位{0x7e, 0x7f}
    int START_FLAG = 32383;
}
