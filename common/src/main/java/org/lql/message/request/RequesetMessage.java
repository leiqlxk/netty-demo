package org.lql.message.request;

import io.netty.buffer.ByteBuf;
import org.lql.message.Message;

/**
 * Title: RequesetMessage <br>
 * ProjectName: coldchain <br>
 * description: 请求报文基类 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 14:27 <br>
 */
public abstract class RequesetMessage extends Message {

    public abstract void deserialize(ByteBuf byteBuf, Message message);
}
