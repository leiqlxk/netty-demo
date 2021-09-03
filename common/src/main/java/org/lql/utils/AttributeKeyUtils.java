package org.lql.utils;

import io.netty.util.AttributeKey;

/**
 * Title: AttributeKeyUtils <br>
 * ProjectName: coldchain <br>
 * description: channel中保存的属性键值 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 17:42 <br>
 */
public class AttributeKeyUtils {

    /**
     * description: 设备id属性 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/9/1 17:43 <br>
     *
     * @throws
     * @param
     * @return io.netty.util.AttributeKey<java.lang.String>
     */
    public static AttributeKey<String> getDeviceid() {
        return AttributeKey.valueOf("DEVICE_ID");
    }
}
