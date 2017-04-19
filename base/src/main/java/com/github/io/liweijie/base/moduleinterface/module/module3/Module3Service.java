package com.github.io.liweijie.base.moduleinterface.module.module3;

import android.support.v4.app.Fragment;

import com.github.io.liweijie.base.moduleinterface.provider.IModule3Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.moduleinterface.router.ServiceManager;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module3Service {

    private static boolean hasModule3() {
        return ModuleManager.getInstance().hasModule(IModule3Provider.MODULE3_MAIN_SERVICE);
    }

    public static Fragment getModule2Fragment(Object... args) {
        if (!hasModule3()) return null;
        return ServiceManager.getInstance().getModule3Provider().newInstance(args);
    }
}
