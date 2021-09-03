package org.lql.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

/**
 * Title: ProcotolFreameDecoder <br>
 * ProjectName: coldchain <br>
 * description: 根据长度字段值拆分包 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/27 17:49 <br>
 */
public class ProcotolFreameDecoder extends LengthFieldBasedFrameDecoder {
    public ProcotolFreameDecoder() {
        this(ByteOrder.LITTLE_ENDIAN, 1024, 2, 2, -2, 0, true);
    }

    public ProcotolFreameDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }
}
