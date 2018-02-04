package cn.krisez.sign;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;
import cn.krisez.sign.bean.User;
import okhttp3.OkHttpClient;

/**
 * Created by Krisez on 2018-01-25.
 */

public class App extends Application {
    public static final String stu_kb = "http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php";

    private static Context mContext;
    private static User sUser;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(3000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        mContext = getApplicationContext();
        Bmob.initialize(this,"1fdea79c19bce2af976f6b920ae4018c");

        sUser = BmobUser.getCurrentUser(User.class);
    }

    public static Context getContext(){
        return mContext;
    }

    public static User getUser(){
        return sUser;
    }
}
