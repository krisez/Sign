package cn.krisez.sign.bean.Seat;

import cn.bmob.v3.BmobObject;
import cn.krisez.sign.bean.Students;

/**
 * Created by Krisez on 2018-01-25.
 * 座位类，通过座位查看迟到，请假次数etc
 */

public class Seats extends BmobObject{
    private int no; //seats number
    private String type;//type of seats(zw or gd)
    private Students belong;//belong somebody
    private int cd;//be late
    private int qj;//have a vacation\
    private String color;//color judge

    Seats() {
    }

    public Seats(int no, String type, Students belong, int cd, int qj, String color) {
        this.no = no;
        this.type = type;
        this.belong = belong;
        this.cd = cd;
        this.qj = qj;
        this.color = color;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Students getBelong() {
        return belong;
    }

    public void setBelong(Students belong) {
        this.belong = belong;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getQj() {
        return qj;
    }

    public void setQj(int qj) {
        this.qj = qj;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
