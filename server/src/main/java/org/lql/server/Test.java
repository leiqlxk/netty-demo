package org.lql.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.lql.constant.ColdConstants;
import org.lql.handler.DeviceHeartBeatMessageHandler;
import org.lql.handler.DeviceNewConnectMessageHandler;
import org.lql.handler.DevicePowerOffMessageHandler;
import org.lql.handler.DevicePowerOnMessageHandler;
import org.lql.protocol.MessageCodecSharable;
import org.lql.protocol.ProcotolFreameDecoder;

import java.io.IOException;

/**
 * Title: Test <br>
 * ProjectName: coldchain <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 11:29 <br>
 */
public class Test {

    public static void main(String[] args) throws IOException {
        EmbeddedChannel channel = new EmbeddedChannel(
                new ProcotolFreameDecoder(),
                new LoggingHandler(LogLevel.DEBUG),
                new MessageCodecSharable(),
                new DeviceNewConnectMessageHandler(),
                new DevicePowerOnMessageHandler(),
                new DevicePowerOffMessageHandler(),
                new DeviceHeartBeatMessageHandler()
        );

        // 新建连接
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{0x7e, 0x7f, 0x26, 0x00, 0x00, 0x07, 0x01, 0x38, 0x36, 0x39, 0x31, 0x34, 0x33, 0x30, 0x35, 0x31, 0x39, 0x31, 0x35, 0x37, 0x30,
                0x38, 0x00, 0x34, 0x36, 0x30, 0x30, 0x34, 0x39, 0x33, 0x35, 0x33, 0x38, 0x30, 0x38, 0x31, 0x31, 0x34, 0x00, (byte) 0x94});

        System.out.println(ColdConstants.START_FLAG == Short.toUnsignedInt(byteBuf.getShort(0)));
        channel.writeInbound(byteBuf);

        // 开机
        ByteBuf byteBuf1 = ByteBufAllocator.DEFAULT.buffer();
        byteBuf1.writeBytes(new byte[]{0x7e, 0x7f, 0x0b, 0x00, 0x00, 0x00, 0x14, 0x0c, 0x16, 0x0d, 0x07, 0x4c, 0x57});

        channel.writeInbound(byteBuf1);

        // 关机
       /* ByteBuf byteBuf2 = ByteBufAllocator.DEFAULT.buffer();
        byteBuf2.writeBytes(new byte[]{0x7e, 0x7f, 0x0b, 0x00, 0x0a, 0x00, 0x14, 0x0c, 0x16, 0x0d, 0x07, 0x4c, 0x57});

        channel.writeInbound(byteBuf2);*/

        // 心跳
        ByteBuf byteBuf3 = ByteBufAllocator.DEFAULT.buffer();
        byteBuf3.writeBytes(new byte[]{0x7e, 0x7f, 0x06, 0x00, 0x00, 0x0a, 0x4d, 0x2d});

        channel.writeInbound(byteBuf3);
    }
}
