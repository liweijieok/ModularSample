package com.github.io.liweijie.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 作者：黎伟杰-子然 on 2017/3/20.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class BaseRecyclerView extends RecyclerView{
    public BaseRecyclerView(Context context) {
        super(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
