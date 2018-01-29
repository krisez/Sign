package cn.krisez.sign.model.login_model;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.krisez.sign.bean.User;

/**
 * Created by Krisez on 2018-01-29.
 */

public class LoginModel implements ILoginModel {
    @Override
    public void getUser(String xh, String mm) {
        User user = new User();
        user.login(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {

            }
        });
    }
}
