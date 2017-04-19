package com.github.io.liweijie.base.moduleinterface.module.home;

import android.app.Activity;

import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.moduleinterface.router.ServiceManager;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class HomeService {

    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IHomeProvider.HOME_MAIN_SERVICE);
    }

    public static void selectedTab(Activity activity, int position) {
        if (!hasModule()) return;
        ServiceManager.getInstance().getHomeProvider().selectedTab(activity, position);
    }

}
