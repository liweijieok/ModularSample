package com.github.io.liweijie.base.util;

import android.text.TextUtils;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Util {
    public static boolean isNull(String... args) {
        if (args == null) return true;
        for (String item : args) {
            if (TextUtils.isEmpty(item)) return true;
        }
        return false;
    }
}
