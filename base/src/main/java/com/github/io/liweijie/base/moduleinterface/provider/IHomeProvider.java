package com.github.io.liweijie.base.moduleinterface.provider;

import android.app.Activity;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public interface IHomeProvider extends IBaseProvider {
    //Service
    String HOME_MAIN_SERVICE = "/home/main/service";
    //开屏
    String HOME_ACT_SPLASH = "/home/act/splash";
    //home主页
    String HOME_ACT_HOME = "/home/act/home";
    String HOME_TABTYPE = "home_tab_type";

    void toast(String msg);

    void selectedTab(Activity activity,int position);



}
