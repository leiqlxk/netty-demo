package org.lql.server;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.lql.protocol.MessageCodecSharable;
import org.lql.protocol.ProcotolFreameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Title: NettyServerChannelInitializer <br>
 * ProjectName: coldchain <br>
 * description: channel初始化处理 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/27 17:45 <br>
 */
@Component
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger log = LoggerFactory.getLogger(NettyServerChannelInitializer.class);

    private final LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
    private final MessageCodecSharable messageCodec = new MessageCodecSharable();


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 添加公共部分handler
        // 拆分包
        channel.pipeline().addLast(new ProcotolFreameDecoder());
        // 日志
        channel.pipeline().addLast(loggingHandler);
        // 编解码
        channel.pipeline().addLast(messageCodec);
        // 空闲检测器
        channel.pipeline().addLast(new IdleStateHandler(540, 0, 0));
        // 处理空闲检测器触发的事件
        channel.pipeline().addLast(new ChannelDuplexHandler() {
            // 用来触发特殊事件
            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                IdleStateEvent event = (IdleStateEvent) evt;
                if ((event.state() == IdleState.READER_IDLE)) {
                    log.error("[{}]通道已经 9 分钟没有读到数据，即将关闭连接", ctx.channel().id());
                    // 释放资源
                    ctx.channel().close();
                }
            }
        });

        // 添加冷链相关handler
        AddHandler.addColdChainHandler(channel);
    }
}
