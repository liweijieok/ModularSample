package com.github.io.liweijie.base.moduleinterface.module.module5;

import com.github.io.liweijie.base.moduleinterface.config.MyBundle;
import com.github.io.liweijie.base.moduleinterface.provider.IModule5Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.moduleinterface.router.MyRouter;

import java.io.Serializable;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module5Intent {
    private static boolean hasModule5() {
        return ModuleManager.getInstance().hasModule(IModule5Provider.MODULE5_MAIN_SERVICE);
    }

    public static void launchModule5(Serializable data) {
        if (!hasModule5()) return;
        MyBundle bundle = new MyBundle();
        bundle.put("data", data);
        MyRouter.newInstance(IModule5Provider.MODULE5_ACT_MAIN)
                .withBundle(bundle)
                .navigation();
    }
}
