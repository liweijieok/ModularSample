package com.github.io.liweijie.base.moduleinterface.router;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.io.liweijie.base.moduleinterface.provider.IAppProvider;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule2Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule3Provider;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：注意，这不是一个完全的单例模式，不能私有化构造器以及属性
 * update by:
 * update day:
 */
public class ServiceManager {
    //服务注入看自己的具体实现
    //自动注入
    @Autowired
    IHomeProvider homeProvider;
    //可以不使用@Autowired，手动发现服务
    IModule1Provider module1Provider;
    IModule2Provider module2Provider;
    IModule3Provider module3Provider;


    public ServiceManager() {
        ARouter.getInstance().inject(this);

    }

    private static final class ServiceManagerHolder {
        private static final ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return ServiceManagerHolder.instance;
    }

    /**
     * @return
     */
    public IHomeProvider getHomeProvider() {
        return homeProvider;
    }


    public IModule1Provider getModule1Provider() {
        return  module1Provider != null ? module1Provider : (module1Provider = ((IModule1Provider) MyRouter.newInstance(IModule1Provider.MODULE1_MAIN_SERVICE).navigation()));
    }

    public IModule2Provider getModule2Provider() {
        return module2Provider != null ? module2Provider : (module2Provider = ((IModule2Provider) MyRouter.newInstance(IModule2Provider.MODULE2_MAIN_SERVICE).navigation()));
    }

    public IModule3Provider getModule3Provider() {
        return module3Provider != null ? module3Provider : (module3Provider = ((IModule3Provider) MyRouter.newInstance(IModule3Provider.MODULE3_MAIN_SERVICE).navigation()));
    }

}
