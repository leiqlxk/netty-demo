package org.lql.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lql.message.request.DeviceHeartBeatMessage;
import org.lql.message.response.GeneralRsponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: DeviceHeartBeatMessageHandler <br>
 * ProjectName: coldchain <br>
 * description: 心跳报文处理 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 13:37 <br>
 */
@ChannelHandler.Sharable
public class DeviceHeartBeatMessageHandler extends SimpleChannelInboundHandler<DeviceHeartBeatMessage> {
    private static final Logger log = LoggerFactory.getLogger(DeviceHeartBeatMessageHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeviceHeartBeatMessage msg) throws Exception {
        System.out.println("msg = " + msg);
        ctx.writeAndFlush(new GeneralRsponse(msg.getSn(), msg.getPktType()));
    }
}
