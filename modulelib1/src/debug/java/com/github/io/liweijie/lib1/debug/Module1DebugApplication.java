package com.github.io.liweijie.lib1.debug;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.io.liweijie.base.BaseApplication;
import com.github.io.liweijie.base.moduleinterface.config.ModuleOptions;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.util.LG;

/**
 * 作者：黎伟杰-子然 on 2017/4/19.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module1DebugApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    private void initARouter() {
        if (LG.isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
        ModuleOptions.ModuleBuilder builder = new ModuleOptions.ModuleBuilder(this)
                .addModule(IHomeProvider.HOME_MAIN_SERVICE, IHomeProvider.HOME_MAIN_SERVICE)
                .addModule(IModule1Provider.MODULE1_MAIN_SERVICE, IModule1Provider.MODULE1_MAIN_SERVICE);
        ModuleManager.getInstance().init(builder.build());
    }
}
