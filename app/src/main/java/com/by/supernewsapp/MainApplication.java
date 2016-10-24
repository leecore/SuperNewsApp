package com.by.supernewsapp;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 木子饼干 on 2016/10/24.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {

            JPushInterface.setDebugMode(true);
        }
        JPushInterface.init(this);
    }
}
