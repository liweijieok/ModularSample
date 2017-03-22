package com.github.io.liweijie.moduleinterface;

import com.github.io.liweijie.moduleinterface.module.ImmuTableMap;

import java.util.Map;

/**
 * 作者：黎伟杰-子然 on 2017/3/20.
 * 邮箱：liweijie@linghit.com
 * description：module管理类
 * update by:
 * update day:
 */
public final class ModuleManager {

    private static ImmuTableMap<String, String> mModules = new ImmuTableMap<>();

    public void addModule(String key, String value) {
        mModules.pul(key, value);
    }

    public void addModules(Map<String, String> modules) {
        mModules.putAll(modules);
    }

    public boolean hasModule(String key) {
        return mModules.containKey(key);
    }

    public String getModule(String key) {
        return mModules.get(key);
    }

}
