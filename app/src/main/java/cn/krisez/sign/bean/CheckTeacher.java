package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

public class CheckTeacher extends BmobObject {
    private String invite;
    private int count;

    public CheckTeacher() {

    }

    public CheckTeacher(String invite, int count) {
        this.invite = invite;
        this.count = count;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
