package com.github.io.liweijie.base.moduleinterface.provider;

import android.support.v4.app.Fragment;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public interface IFragmentProvider extends IBaseProvider {

    Fragment newInstance(Object... args);

}
