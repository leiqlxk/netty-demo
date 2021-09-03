package org.lql.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.cold.session.SessionFactory;
import org.cold.utils.AttributeKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: QuitHandler <br>
 * ProjectName: learn-netty <br>
 * description: 退出处理 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 */
@ChannelHandler.Sharable
public class QuitHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuitHandler.class);

    // 断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        if (SessionFactory.getSession().hasDevice(ctx.channel())) {
            SessionFactory.getSession().unbind(ctx.channel());
            LOGGER.error("=========================通道[{}] 已断开，设备号：[{}]", ctx.channel().id(), SessionFactory.getSession().getAttribute(ctx.channel(), AttributeKeyUtils.getDeviceid()));
        }else {
            LOGGER.error("=========================通道[{}] 已断开，此通道无设备id", ctx.channel().id());
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("==================={} 新建连接", ctx.channel().id());
    }

    // 异常断开
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (!SessionFactory.getSession().hasDevice(ctx.channel())) {
            LOGGER.error("=====================通道[{}] 发生异常， 此连接无设备号，将关闭，异常是 {}", ctx.channel().id(), cause);
            ctx.channel().close();
        }else  {
            LOGGER.error("=====================通道[{}] 发生异常，设备号：[{}], 异常是 {}", ctx.channel().id(), SessionFactory.getSession().getAttribute(ctx.channel(), AttributeKeyUtils.getDeviceid()), cause);
        }
    }
}
