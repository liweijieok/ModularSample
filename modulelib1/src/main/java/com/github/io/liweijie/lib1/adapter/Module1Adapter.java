package com.github.io.liweijie.lib1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.io.liweijie.base.view.adapter.BaseAdapter;
import com.github.io.liweijie.base.view.adapter.VH;
import com.github.io.liweijie.lib1.R;
import com.github.io.liweijie.lib1.bean.Books;

import java.util.List;

/**
 * 作者：黎伟杰-子然 on 2017/4/17.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module1Adapter extends BaseAdapter<Books, Module1Adapter.Module1Holder> {

    public Module1Adapter(List<Books> mDatas) {
        super(mDatas);
    }

    @Override
    protected Module1Holder getHolder(ViewGroup parent, int viewType) {
        return new Module1Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module1_fragment_main_item, parent, false));
    }

    class Module1Holder extends VH<Books> {

        private TextView name;
        private TextView author;

        public Module1Holder(View itemView) {
            super(itemView);
            name = findvViewById(R.id.module1_fragment_main_item_name);
            author = findvViewById(R.id.module1_fragment_main_item_author);
        }

        @Override
        protected void setData(Books data) {
            name.setText(data.getName());
            author.setText(data.getAuthor());
        }
    }
}
