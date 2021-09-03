package org.lql.message.response;


import org.lql.constant.ColdConstants;
import org.lql.message.Message;

/**
 * Title: GeneralRsponse <br>
 * ProjectName: coldchain <br>
 * description: 通用回复 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/1 14:40 <br>
 */
public class GeneralRsponse extends Message {
    // 回复的报文类型
    private Byte ackType;

    public GeneralRsponse(Byte sn, Byte ackType) {
        super(ColdConstants.START_FLAG, 6, sn, Integer.valueOf(ColdConstants.PACKET_ACK).byteValue());
        this.ackType = ackType;
    }

    public Byte getAckType() {
        return ackType;
    }

    public void setAckType(Byte ackType) {
        this.ackType = ackType;
    }

    @Override
    public String toString() {
        return "GeneralRsponse{" +
                "ackType=" + ackType +
                "} " + super.toString();
    }
}
