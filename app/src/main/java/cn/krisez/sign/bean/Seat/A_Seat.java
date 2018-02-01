package cn.krisez.sign.bean.Seat;

import cn.krisez.sign.bean.Students;

/**
 * Created by Krisez on 2018-01-26.
 */

public class A_Seat extends Seats {
//    private int no; //seats number
//    private String type;//type of seats(zw or gd)
//    private Students belong;//belong somebody
//    private int cd;//be late
//    private int qj;//have a vacation

    public A_Seat() {
        super();
    }

    public A_Seat(int no, String type, Students belong, int cd, int qj, String color) {
        super(no, type, belong, cd, qj, color);
    }

    public int getNo() {
        return super.getNo();
    }

    public void setNo(int no) {
        super.setNo(no);
    }

    public String getType() {
        return super.getType();
    }

    public void setType(String type) {
        super.setType(type);
    }

    public Students getBelong() {
        return super.getBelong();
    }

    public void setBelong(Students belong) {
        super.setBelong(belong);
    }

    public int getCd() {
        return super.getCd();
    }

    public void setCd(int cd) {
        super.setCd(cd);
    }

    public int getQj() {
        return super.getQj();
    }

    public void setQj(int qj) {
        super.setQj(qj);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }
}
