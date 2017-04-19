package com.github.io.liweijie.base;

import android.app.Application;

import com.github.io.liweijie.base.util.LG;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LG.isDebug = true;
    }
}
