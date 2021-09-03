package org.lql.message.request;

import io.netty.buffer.ByteBuf;
import org.lql.message.Message;

/**
 * Title: DevicePowerOnRequest <br>
 * ProjectName: coldchain <br>
 * description: 设备开机 报文 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/8/30 14:01 <br>
 */
public class DevicePowerOnMessage extends RequesetMessage {

    // 2021年，只写21年
    private Byte year;

    private Byte month;

    private Byte day;

    private Byte hour;

    private Byte minute;

    private Byte second;

    // 信号质量 Cesq > 55 满格（4格）  Cesq > 45  3格  Cesq > 35  2格  Cesq > 25  1格
    private Byte cesq;

    @Override
    public void deserialize(ByteBuf byteBuf, Message message) {
        this.year = byteBuf.readByte();
        this.month = byteBuf.readByte();
        this.day = byteBuf.readByte();
        this.hour = byteBuf.readByte();
        this.minute = byteBuf.readByte();
        this.second = byteBuf.readByte();
        this.cesq = byteBuf.readByte();
    }


    public Byte getYear() {
        return year;
    }

    public void setYear(Byte year) {
        this.year = year;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public Byte getDay() {
        return day;
    }

    public void setDay(Byte day) {
        this.day = day;
    }

    public Byte getHour() {
        return hour;
    }

    public void setHour(Byte hour) {
        this.hour = hour;
    }

    public Byte getMinute() {
        return minute;
    }

    public void setMinute(Byte minute) {
        this.minute = minute;
    }

    public Byte getSecond() {
        return second;
    }

    public void setSecond(Byte second) {
        this.second = second;
    }

    public Byte getCesq() {
        return cesq;
    }

    public void setCesq(Byte cesq) {
        this.cesq = cesq;
    }

    @Override
    public String toString() {
        return "DevicePowerOnMessage{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", cesq=" + cesq +
                "} " + super.toString();
    }
}
