package org.lql.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import org.lql.message.request.RequesetMessage;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Title: Serializer <br>
 * ProjectName: learn-netty <br>
 * description: 用于扩展序列化、反序列化算法 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 */
public interface Serializer {

    // 反序列化方法
    <T> T deserialize(Class<T> clazz, ByteBuf byteBuf) throws Exception;

    // 序列化方法
    <T> byte[] serialize(T object) throws Exception;

    enum Algorithm implements Serializer{

        // 自定义序列化
        CUSTOMIZE{
            @Override
            public <T> T deserialize(Class<T> clazz, ByteBuf byteBuf) throws Exception {
                try {
                    RequesetMessage message = (RequesetMessage) clazz.newInstance();
                    // 处理公共部分
                    short startFlag = byteBuf.readShortLE();
                    short length = byteBuf.readShortLE();
                    byte sn = byteBuf.readByte();
                    byte pktType = byteBuf.readByte();
                    message.setStartFlag(Short.toUnsignedInt(startFlag));
                    message.setLength(Short.toUnsignedInt(length));
                    message.setSn(sn);
                    message.setPktType(pktType);

                    // 处理个性化
                    message.deserialize(byteBuf, message);

                    // 处理CRC字节
                    // byteBuf.readByte();
                    return (T) message;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("消息转换失败");
                }
            }

            @Override
            public <T> byte[] serialize(T object) {
                return new byte[0];
            }
        },

        // jdk序列化实现
        Java {
            @Override
            public <T> T deserialize(Class<T> clazz, ByteBuf byteBuf) throws Exception {
                ObjectInputStream osi = null;
                int length = byteBuf.readableBytes();
                byte[] bytes = new byte[length];
                byteBuf.readBytes(bytes, 0, length);
                try {
                    osi = new ObjectInputStream(new ByteArrayInputStream(bytes));
                    return (T) osi.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    throw new Exception("反序列化失败");
                }finally {
                    if (osi != null) {
                        try {
                            osi.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public <T> byte[] serialize(T object) throws Exception {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(object);
                    return bos.toByteArray();
                }catch (IOException e) {
                    throw new Exception("序列化失败");
                }
            }
        },

        // json序列化实现
        Json {
            @Override
            public <T> T deserialize(Class<T> clazz, ByteBuf byteBuf) {
                int length = byteBuf.readableBytes();
                byte[] bytes = new byte[length];
                byteBuf.readBytes(bytes, 0, length);
                String json = new String(bytes, StandardCharsets.UTF_8);
                return JSON.parseObject(json, clazz);
            }

            @Override
            public <T> byte[] serialize(T object) {
                String json = JSON.toJSONString(object);
                return json.getBytes(StandardCharsets.UTF_8);
            }
        }
    }
}
