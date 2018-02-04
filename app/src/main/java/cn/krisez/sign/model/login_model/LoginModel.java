package cn.krisez.sign.model.login_model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.krisez.sign.App;
import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.User;
import cn.krisez.sign.persenter.login_presenter.LoginListener;
import cn.krisez.sign.persenter.login_presenter.LoginPresenter;
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
                if (e == null) {
                    loginTable(xh);
                    saveStudent(xh);
                    SharedPreferenceUtil.setTable(xh,"");
                    listener.success();
                }else listener.failed(e.getMessage());
            }
        });

    }

    @Override
    public void register(final String xh, final String mm, Students students, final LoginListener listener) {
        students.save();
        User user = new User();
        user.setUsername(xh);
        user.setPassword(mm);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                if (e == null) {
                    u.setPassword(mm);
                    loginTable(xh);
                    laterLogin(u, listener);
                } else listener.failed(e.getMessage());
            }
        });
    }

    private void laterLogin(User user, final LoginListener listener) {
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                saveStudent(user.getUsername());
                listener.success();
            }
        });
    }

    private void loginTable(final String xh) {
        OkHttpUtils.get().addParams("xh", xh).url(App.stu_kb).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                String data = response.body().string();
                SharedPreferenceUtil.setTable(xh, data);
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

    private void saveStudent(String xh) {
        BmobQuery<Students> query = new BmobQuery<>();
        query.addWhereEqualTo("xh", xh)
                .findObjects(new FindListener<Students>() {
                    @Override
                    public void done(List<Students> list, BmobException e) {
                        Students students = list.get(0);
                        SharedPreferenceUtil.saveStudent(students);
                    }
                });
    }
}
