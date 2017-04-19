package com.github.io.liweijie.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * 作者：黎伟杰-子然 on 2017/3/21.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    protected View mContentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        log("OnCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        log("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        log("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        log("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        log("onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        log("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //解决嵌套 Fragment 的bug
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log("onCreateView");
        return mContentView = getContentView(inflater, container, savedInstanceState);
    }

    public abstract View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public <T extends View> T findViewById(@IdRes int id) {
        return (T) mContentView.findViewById(id);
    }

    protected void log(String msg) {
        Log.i(TAG, msg);
    }
}
