package cn.krisez.sign.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.krisez.sign.bean.Seat.Seats;

/**
 * Created by Krisez on 2018-01-25.
 * type a,b,c,d,e,f,g....
 */

public class ClassSeats extends BmobObject {
    private String type;//教室类型
    private List<Seats> mSeats;//座位

    public ClassSeats() {
    }

    public List<Seats> getSeats() {
        return mSeats;
    }

    public void setSeats(List<Seats> seats) {
        mSeats = seats;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
