package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-02-18.
 */

public class ShowStuState extends BmobObject{
    private String xh;
    private String name;
    private boolean state;

    public ShowStuState() {
    }

    public ShowStuState(String xh, String name, boolean state) {
        this.xh = xh;
        this.name = name;
        this.state = state;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
