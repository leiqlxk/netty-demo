package org.lql.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lql.message.request.DevicePowerOffMessage;
import org.lql.message.response.GeneralRsponse;

/**
 * Title: DevicePowerOffMessageHandler <br>
 * ProjectName: coldchain <br>
 * description: 关机报文处理 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 13:25 <br>
 */
@ChannelHandler.Sharable
public class DevicePowerOffMessageHandler extends SimpleChannelInboundHandler<DevicePowerOffMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DevicePowerOffMessage msg) throws Exception {
        System.out.println("msg = " + msg);
        ctx.writeAndFlush(new GeneralRsponse(msg.getSn(), msg.getPktType()));
    }
}
