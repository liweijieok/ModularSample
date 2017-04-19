package com.github.io.liweijie.lib2;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.io.liweijie.base.moduleinterface.provider.IModule2Provider;

import static com.github.io.liweijie.base.moduleinterface.provider.IModule2Provider.MODULE2_MAIN_SERVICE;

/**
 * 作者：黎伟杰-子然 on 2017/4/17.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
@Route(path = MODULE2_MAIN_SERVICE)
public class Module2Provider implements IModule2Provider {
    @Override
    public Fragment newInstance(Object... args) {
        return Module2Fragment.newInstance(args);
    }

    @Override
    public void init(Context context) {

    }
}
