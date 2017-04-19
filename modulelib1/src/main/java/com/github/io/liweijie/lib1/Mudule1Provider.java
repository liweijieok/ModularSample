package com.github.io.liweijie.lib1;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider;

import static com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider.MODULE1_MAIN_SERVICE;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
@Route(path = MODULE1_MAIN_SERVICE)
public class Mudule1Provider implements IModule1Provider {

    @Override
    public Fragment newInstance(Object... args) {
        return Module1Fragment.newInstance(args);
    }

    @Override
    public void init(Context context) {

    }
}
