package org.lql.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * Title: NettyServer <br>
 * ProjectName: coldchain <br>
 * description: netty服务端 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/27 17:28 <br>
 */
@Component
public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    @Value("${netty.port}")
    private Integer port;

    private EventLoopGroup boss;
    private EventLoopGroup work;

    public void  run() {
        boss = new NioEventLoopGroup();
        work = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerChannelInitializer());

            // 设置全连接队列大小
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            // 关闭默认两小时检测
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, false);
            // 关闭negla算法
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            //使用指定的端口设置套接字地址
            bootstrap.localAddress(new InetSocketAddress(port));

            // 启动服务器
            ChannelFuture channelFuture = bootstrap.bind().sync();
            LOGGER.info("服务器已启动.....");
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e) {
            LOGGER.error("服务器异常error", e);
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
