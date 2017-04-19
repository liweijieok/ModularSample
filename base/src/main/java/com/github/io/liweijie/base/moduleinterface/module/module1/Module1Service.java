package com.github.io.liweijie.base.moduleinterface.module.module1;

import android.support.v4.app.Fragment;

import com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.moduleinterface.router.ServiceManager;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module1Service {
    private static boolean hasModule1() {
        return ModuleManager.getInstance().hasModule(IModule1Provider.MODULE1_MAIN_SERVICE);
    }

    public static Fragment getModule1Frgment(Object... args) {
        if(!hasModule1()) return null;
        return ServiceManager.getInstance().getModule1Provider().newInstance(args);
    }
}
