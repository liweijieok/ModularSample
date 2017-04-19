package com.github.io.liweijie.base.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：黎伟杰-子然 on 2017/4/13.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public abstract class BaseAdapter<D, V extends VH> extends RecyclerView.Adapter<V> {

    private List<D> mDatas;

    public BaseAdapter(List<D> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder(parent, viewType);
    }

    protected abstract V getHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        holder.init(this.mDatas.get(position));
    }


}
