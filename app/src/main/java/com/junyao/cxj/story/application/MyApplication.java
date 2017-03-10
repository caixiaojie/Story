package com.junyao.cxj.story.application;

import android.app.Application;
import android.content.Context;


/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //ShareSDK.initSDK(context);
    }
    public static Context getContext()
    {
        return context;
    }
}
