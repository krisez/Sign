package cn.krisez.sign;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by Krisez on 2018-01-25.
 */

public class App extends Application {

    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Bmob.initialize(this,"1fdea79c19bce2af976f6b920ae4018c");
    }

    public Context getContext(){
        return mContext;
    }
}
