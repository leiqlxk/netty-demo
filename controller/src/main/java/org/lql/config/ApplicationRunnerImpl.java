package org.lql.config;


import org.lql.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * description: 系统启动完成后执行，启动netty服务端 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/3 13:32 <br>
 *
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {


    private static final Logger LOG = LoggerFactory.getLogger(ApplicationRunnerImpl.class);

    /**
     * 程序运行端口
     */
    @Value("${server.port}")
    private String port;

    private static final String CAT1_REDIS_KEY_PRIFIX = "device:cold:%s:*";

    /**
     * netty运行使用端口
     */
    @Value("${netty.port}")
    private String nettyPort;

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //server启动
        nettyServer.run();
        LOG.info("程序启动成功，web监听端口：{}，netty监听端口：{}", port, nettyPort);
    }

}
