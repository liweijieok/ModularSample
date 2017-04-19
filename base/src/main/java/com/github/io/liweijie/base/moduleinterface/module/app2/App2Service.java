package com.github.io.liweijie.base.moduleinterface.module.app2;

import com.github.io.liweijie.base.moduleinterface.provider.IAppProvider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class App2Service {
    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IAppProvider.APP_MAIN_SERVICE);
    }

}
