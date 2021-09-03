package org.lql.protocol;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.lql.constant.ColdConstants;
import org.lql.message.Message;
import org.lql.message.response.GeneralRsponse;
import org.lql.utils.CRC8Utils;
import org.lql.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Title: MessageCodecSharable <br>
 * ProjectName: coldchain <br>
 * description: 编解码 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 11:40 <br>
 */
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {
    private static final Logger log = LoggerFactory.getLogger(MessageCodecSharable.class);

    // 编码
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> list) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.alloc().buffer();
        GeneralRsponse msg = (GeneralRsponse) message;
        log.info("回复消息：{}", msg);
        // 写入起始标志位
        byteBuf.writeShort(msg.getStartFlag());

        // 写入长度字段
        byteBuf.writeShortLE(msg.getLength());

        // 写入sn
        byteBuf.writeByte(msg.getSn());

        // 写入pktType
        byteBuf.writeByte(msg.getPktType());

        // 写入ackType
        byteBuf.writeByte(msg.getAckType());

        int crc8 = CRC8Utils.caclCRC8(byteBuf);

        byteBuf.writeByte(Integer.valueOf(crc8).byteValue());
        list.add(byteBuf);
    }

    // 解码
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.info("开始解码：{}", LogUtils.byteBufToString(byteBuf));
        // 校验起始标志位
        if (ColdConstants.START_FLAG != Short.toUnsignedInt(byteBuf.getShort(0))) {
            log.error("==============起始标志位校验不通过，数据{}处理失败", LogUtils.byteBufToString(byteBuf));

            throw new Exception("起始标志校验失败，消息：[" + LogUtils.byteBufToString(byteBuf) + "]");
        }

        // crc8 校验
        boolean isChecked = CRC8Utils.checkCRC8(byteBuf);

        if (!isChecked) {
            log.error("==============crc8校验不通过，数据{}处理失败", LogUtils.byteBufToString(byteBuf));

            throw new Exception("crc8校验失败，消息：[" + LogUtils.byteBufToString(byteBuf) + "]");
        }

        // 获取报文类型
        byte pktType = byteBuf.getByte(5);

        // 找到对应的消息类型
        Class<? extends Message> messageClass = Message.getMessageClass(pktType);

        // 获取报文对象
        Serializer.Algorithm algorithm = Serializer.Algorithm.values()[0];
        Message message = algorithm.deserialize(messageClass, byteBuf);

        list.add(message);
        log.info("解码完成：[{}]", message.toString());

    }
}
