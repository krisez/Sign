package cn.krisez.sign.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Krisez on 2018-01-29.
 */

public class User extends BmobUser {
    private String headImg;
    private String type;

    public User() {
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
