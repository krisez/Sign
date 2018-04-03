package cn.krisez.sign.model.login_model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.krisez.sign.App;
import cn.krisez.sign.bean.CheckTeacher;
import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;
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
 * 1学生，2老师
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
                    loginTable(xh, Integer.parseInt(u.getType()));
                    if(u.getType().equals("1"))
                    saveStudent(xh);
                    else saveTeacher(xh);
                    SharedPreferenceUtil.setTable(xh, "");
                    App.setUser(u);
                    listener.success();
                } else {
                    if (e.getErrorCode() == 101)
                        listener.failed("用户名或密码不正确");
                }
            }
        });

    }

    @Override
    public void register(final String xh, final String mm, Students students, final LoginListener listener) {
        students.save();
        User user = new User();
        user.setUsername(xh);
        user.setPassword(mm);
        user.setType("1");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                if (e == null) {
                    u.setPassword(mm);
                    SharedPreferenceUtil.setTable(xh, "");
                    laterLogin(u, listener);
                    loginTable(xh, 1);
                } else listener.failed(e.getMessage());
            }
        });
    }

    @Override
    public void teacher(final String gh, final String mm, Teacher teacher, final LoginListener listener) {
        teacher.save();
        User user = new User();
        user.setUsername(gh);
        user.setPassword(mm);
        user.setType("2");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                if (e == null) {
                    u.setPassword(mm);
                    SharedPreferenceUtil.setTable(gh, "");
                    laterLogin(u, listener);
                    loginTable(gh, 2);
                } else listener.failed(e.getMessage());
            }
        });
    }

    @Override
    public void checkTeacher(String s, final LoginListener listener) {
        BmobQuery<CheckTeacher> query = new BmobQuery<>();
        query.addWhereEqualTo("invite", s)
                .findObjects(new FindListener<CheckTeacher>() {
            @Override
            public void done(List<CheckTeacher> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        CheckTeacher checkTeacher = list.get(0);
                        int c = checkTeacher.getCount();
                        if(c > 0){
                            c = c-1;
                            checkTeacher.setCount(c);
                            checkTeacher.update( new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    listener.invite();
                                }
                            });
                        }else listener.failed("请换一个邀请码");
                    } else {
                        listener.failed("不存在");
                    }
                } else {
                    e.printStackTrace();
                    listener.failed(e.getMessage());
                }
            }
        });
    }

    private void laterLogin(User user, final LoginListener listener) {
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user.getType().equals("1")) {
                    saveStudent(user.getUsername());
                } else saveTeacher(user.getUsername());
                App.setUser(user);
                listener.success();
            }
        });
    }


    private void loginTable(final String xh, int i) {
        String url = "";
        String params = "";
        if (i == 1) {
            url = App.stu_kb;
            params = "xh";
        } else {
            url = App.tea_kb;
            params = "teaId";
        }
        OkHttpUtils.get().addParams(params, xh).url(url).build().execute(new Callback() {
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


    private void saveTeacher(String username) {
        BmobQuery<Teacher> query = new BmobQuery<>();
        query.addWhereEqualTo("gh", username)
                .findObjects(new FindListener<Teacher>() {
                    @Override
                    public void done(List<Teacher> list, BmobException e) {
                        Teacher teacher = list.get(0);
                        SharedPreferenceUtil.saveTeacher(teacher);
                    }
                });
    }
}
