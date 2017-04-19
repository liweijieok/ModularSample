package com.github.io.liweijie.lib1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.io.liweijie.base.BaseRecyclerFragment;
import com.github.io.liweijie.lib1.adapter.Module1Adapter;
import com.github.io.liweijie.lib1.bean.Books;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：黎伟杰-子然 on 2017/4/16.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module1Fragment extends BaseRecyclerFragment {

    public static Module1Fragment newInstance(Object... args) {
        return new Module1Fragment();
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.module1_fragment_main, container,false);
    }


    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new Module1Adapter(initTestData());
    }

    private List<Books> initTestData() {
        List<Books> mDatas = new ArrayList<>();
        mDatas.add(new Books("书名A", "作者A"));
        mDatas.add(new Books("书名B", "作者B"));
        mDatas.add(new Books("书名C", "作者C"));
        mDatas.add(new Books("书名D", "作者D"));
        mDatas.add(new Books("书名E", "作者E"));
        mDatas.add(new Books("书名F", "作者F"));
        mDatas.add(new Books("书名G", "作者G"));
        mDatas.add(new Books("书名H", "作者H"));
        mDatas.add(new Books("书名I", "作者I"));
        mDatas.add(new Books("书名J", "作者J"));
        mDatas.add(new Books("书名K", "作者K"));
        return mDatas;
    }
}
