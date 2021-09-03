package org.lql.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * Title: LogUtils <br>
 * ProjectName: coldchain <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 13:30 <br>
 */
public class LogUtils {

    public static String byteBufToString(ByteBuf byteBuf) {
        return toHexString(ByteBufUtil.decodeHexDump(ByteBufUtil.hexDump(byteBuf)));
    }

    public static String toHexString(byte[] a) {
        if (a == null) {
            return "null";
        }
        int iMax = a.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(Integer.toHexString(a[i] & 0xFF));
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }
}
