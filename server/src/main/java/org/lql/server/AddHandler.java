package org.lql.server;

import io.netty.channel.socket.SocketChannel;
import org.lql.handler.*;

/**
 * Title: Add <br>
 * ProjectName: coldchain <br>
 * description: 添加各种设备的handler <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 14:29 <br>
 */
public class AddHandler {
    private static final DeviceNewConnectMessageHandler DEVICE_NEW_CONNECT_MESSAGE_HANDLER = new DeviceNewConnectMessageHandler();
    private static final DevicePowerOnMessageHandler DEVICE_POWER_ON_MESSAGE_HANDLER = new DevicePowerOnMessageHandler();
    private static final DevicePowerOffMessageHandler DEVICE_POWER_OFF_MESSAGE_HANDLER = new DevicePowerOffMessageHandler();
    private static final DeviceHeartBeatMessageHandler DEVICE_HEART_BEAT_MESSAGE_HANDLER = new DeviceHeartBeatMessageHandler();
    private static final QuitHandler QUIT_HANDLER = new QuitHandler();


    // 添加冷链处理器
    public static final void addColdChainHandler(SocketChannel channel) {

        // 新建连接
        channel.pipeline().addLast(DEVICE_NEW_CONNECT_MESSAGE_HANDLER);
        // 开机
        channel.pipeline().addLast(DEVICE_POWER_ON_MESSAGE_HANDLER);
        // 关机
        channel.pipeline().addLast(DEVICE_POWER_OFF_MESSAGE_HANDLER);
        // 心跳
        channel.pipeline().addLast(DEVICE_HEART_BEAT_MESSAGE_HANDLER);
        // 连接断开Handler
        channel.pipeline().addLast(QUIT_HANDLER);
    }
}
