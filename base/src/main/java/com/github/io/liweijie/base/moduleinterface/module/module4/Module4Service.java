package com.github.io.liweijie.base.moduleinterface.module.module4;

import com.github.io.liweijie.base.moduleinterface.provider.IModule4Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module4Service {
    private boolean hasModule1() {
        return ModuleManager.getInstance().hasModule(IModule4Provider.MODULE4_MAIN_SERVICE);
    }
}
