package com.github.io.liweijie.lib5;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.io.liweijie.base.moduleinterface.provider.IModule5Provider;

/**
 * 作者：黎伟杰-子然 on 2017/4/18.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
@Route(path = IModule5Provider.MODULE5_MAIN_SERVICE)
public class Module5Provider implements IModule5Provider {
    @Override
    public Fragment newInstance(Object... args) {
        return null;
    }

    @Override
    public void init(Context context) {

    }
}
