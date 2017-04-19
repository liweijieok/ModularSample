package com.github.io.liweijie.base.moduleinterface.config;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：链式调用
 * update by:
 * update day:
 */
public class MyBundle {
    private Bundle bundle;

    public MyBundle() {
        bundle = new Bundle();
    }

    public MyBundle put(String key, int value) {
        bundle.putInt(key, value);
        return this;
    }

    public MyBundle put(String key, long value) {
        bundle.putLong(key, value);
        return this;
    }

    public MyBundle put(String key, String value) {
        if (TextUtils.isEmpty(value)) {
            return this;
        }
        bundle.putString(key, value);
        return this;
    }

    public MyBundle put(String key, Serializable value) {
        if (value == null) {
            return this;
        }
        bundle.putSerializable(key, value);
        return this;
    }

    public MyBundle put(String key, Parcelable value) {
        if (value == null) {
            return this;
        }
        bundle.putParcelable(key, value);
        return this;
    }

    public MyBundle put(String key, String[] arrays) {
        if (arrays == null) {
            return this;
        }
        bundle.putStringArray(key, arrays);
        return this;
    }

    public Bundle build() {
        return bundle;
    }
}
