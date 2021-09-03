package org.lql.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description: 处理MQ信息 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/3 13:33 <br>
 *
 */
@Component
public class RabbitMqUtils {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqUtils.class);

    @Autowired
    private RabbitTemplate amqpTemplate;

    /**
     * 数据发往消息中间件
     *
     * @param exchange   交换机
     * @param routingKey 油路key
     * @param data       数据
     * @return success
     */
    public synchronized String send(String exchange, String routingKey, String data) {
        try {
            LOG.info("发送消息中间件数据：[{}],exchange:[{}],routingKey:[{}]", data, exchange, routingKey);
            amqpTemplate.convertAndSend(exchange, routingKey, data);
        } catch (AmqpException e) {
            LOG.error(e.getMessage());
            return "fail";
        }
        return "success";
    }

    @PostConstruct
    private void initRabbitMq() {

        amqpTemplate.setReturnCallback((message, replyCode, replyText, exchange1, routingKey1) -> {
            LOG.info("消息丢失，exchange1:{},routingKey1:{},数据：{}",exchange1,routingKey1, new String(message.getBody()));
            amqpTemplate.convertAndSend(exchange1,routingKey1, new String(message.getBody()));
        });

        amqpTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack == false) {
                LOG.info("消息丢失，数据：{}",correlationData.getReturnedMessage().getBody());
                //rabbitTemplate.convertAndSend(exchange,routingKey, new String(correlationData.getReturnedMessage().getBody()));
            }
        });
    }
}
