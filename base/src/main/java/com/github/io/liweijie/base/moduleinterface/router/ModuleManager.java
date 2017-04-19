package com.github.io.liweijie.base.moduleinterface.router;


import com.github.io.liweijie.base.moduleinterface.config.ModuleOptions;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class ModuleManager {

    private ModuleOptions options;

    private ModuleManager() {
    }

    private static class ModuleManagerHolder {
        private static final ModuleManager instance = new ModuleManager();
    }

    public static ModuleManager getInstance() {
        return ModuleManagerHolder.instance;

    }

    public void init(ModuleOptions options) {
        if (this.options == null && options != null) {
            this.options = options;
        }
    }

    public ModuleOptions getOptions() {
        return options;
    }

    public boolean hasModule(String key) {
        return options.hasModule(key);
    }


}
