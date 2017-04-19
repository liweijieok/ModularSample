package com.github.io.liweijie.base.moduleinterface.module.module5;

import com.github.io.liweijie.base.moduleinterface.provider.IModule5Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module5Service {
    private boolean hasModule1() {
        return ModuleManager.getInstance().hasModule(IModule5Provider.MODULE5_MAIN_SERVICE);
    }
}
