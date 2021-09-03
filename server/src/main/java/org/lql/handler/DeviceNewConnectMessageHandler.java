package org.lql.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lql.message.request.DeviceNewConnectMessage;
import org.lql.message.response.GeneralRsponse;
import org.lql.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: DeviceNewConnectHandler <br>
 * ProjectName: coldchain <br>
 * description: 设备新建立连接 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 11:35 <br>
 */
@ChannelHandler.Sharable
public class DeviceNewConnectMessageHandler extends SimpleChannelInboundHandler<DeviceNewConnectMessage> {
    private static final Logger log = LoggerFactory.getLogger(DeviceNewConnectMessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeviceNewConnectMessage msg) throws Exception {
        log.info("=========================设备：[{}]建立连接，通道号[{}]", msg.getDeviceId(), ctx.channel().id());
        SessionFactory.getSession().bind(ctx.channel(), msg.getDeviceId());
        ctx.writeAndFlush(new GeneralRsponse(msg.getSn(), msg.getPktType()));
    }
}
