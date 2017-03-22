package com.github.io.liweijie.moduleinterface.module;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：黎伟杰-子然 on 2017/3/20.
 * 邮箱：liweijie@linghit.com
 * description：不可变数map
 * update by:
 * update day:
 */
@SuppressWarnings("ALL")
public final class ImmuTableMap<K, V> {
    private Map<K, V> mMap;

    public void pul(K k, V v) {
        if (mMap == null) {
            mMap = new HashMap<>();
        }
        mMap.put(k, v);
    }

    public void putAll(Map<K, V> map) {
        if (map == null) {
            return;
        }
        if (mMap == null) {
            mMap = new HashMap<>();
        }
        mMap.putAll(map);
    }

    public V get(K k) {
        if (k == null || mMap == null)
            return null;
        return mMap.get(k);
    }

    public boolean containKey(K k) {
        return !(null == k || mMap == null) && mMap.containsKey(k);
    }

    public boolean containValue(V v) {
        return !(null == v || mMap == null) && mMap.containsValue(v);
    }

}
