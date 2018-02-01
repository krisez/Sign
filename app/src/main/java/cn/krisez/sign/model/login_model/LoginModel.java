package cn.krisez.sign.model.login_model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.krisez.sign.App;
import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.bean.User;
import cn.krisez.sign.persenter.login_presenter.LoginListener;
import cn.krisez.sign.persenter.table_presenter.TableListener;
import cn.krisez.sign.utils.SharedPreferenceUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Krisez on 2018-01-29.
 * 登录model
 */

public class LoginModel implements ILoginModel {

    @Override
    public void getUser(final String xh, String mm, final LoginListener listener) {
        User user = new User();
        user.setUsername(xh);
        user.setPassword(mm);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                SharedPreferenceUtil.setUser(u);
                loginTable(xh);
                listener.success();
            }
        });

    }

    private void loginTable(final String xh) {
        OkHttpUtils.get().addParams("xh",xh).url(App.stu_kb).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                String data = response.body().string();
                SharedPreferenceUtil.setTable(xh,data);
                return 0;
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Object response, int id) {

            }
        });
    }
}
