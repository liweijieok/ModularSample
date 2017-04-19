package com.github.io.liweijie;


import com.alibaba.android.arouter.launcher.ARouter;
import com.github.io.liweijie.base.BaseApplication;
import com.github.io.liweijie.base.moduleinterface.provider.IModule2Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule3Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule4Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.moduleinterface.config.ModuleOptions;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider;
import com.github.io.liweijie.base.util.LG;

/**
 * 作者：黎伟杰-子然 on 2017/3/26.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class CustomApplication extends BaseApplication {
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
                .addModule(IModule1Provider.MODULE1_MAIN_SERVICE, IModule1Provider.MODULE1_MAIN_SERVICE)
                .addModule(IModule2Provider.MODULE2_MAIN_SERVICE, IModule2Provider.MODULE2_MAIN_SERVICE)
                .addModule(IModule3Provider.MODULE3_MAIN_SERVICE, IModule3Provider.MODULE3_MAIN_SERVICE)
                .addModule(IModule4Provider.MODULE4_MAIN_SERVICE, IModule4Provider.MODULE4_MAIN_SERVICE);

        ModuleManager.getInstance().init(builder.build());
    }
}
