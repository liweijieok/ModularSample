package com.github.io.liweijie.base.moduleinterface.module.home;

import com.github.io.liweijie.base.moduleinterface.config.MyBundle;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;
import com.github.io.liweijie.base.moduleinterface.router.MyRouter;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class HomeIntent {
    public static void launchHome(int tabType) {
        //HomeActivity
        MyBundle bundle = new MyBundle();
        bundle.put(IHomeProvider.HOME_TABTYPE, tabType);
        MyRouter.newInstance(IHomeProvider.HOME_ACT_HOME)
                .withBundle(bundle)
                .navigation();
    }
}
