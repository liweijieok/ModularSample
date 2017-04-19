package com.github.io.liweijie.lib2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.io.liweijie.base.BaseFragment;
import com.github.io.liweijie.base.moduleinterface.module.home.HomeService;
import com.github.io.liweijie.lib3.R;

/**
 * 作者：黎伟杰-子然 on 2017/3/26.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module3Fragment extends BaseFragment implements View.OnClickListener {

    private Button tab1;
    private Button tab2;

    public static Module3Fragment newInstance(Object... args) {
        return new Module3Fragment();
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.module2_main_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab1 = findViewById(R.id.module2_main_fragment_tab1);
        tab2 = findViewById(R.id.module2_main_fragment_tab2);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.module2_main_fragment_tab1) {
            HomeService.selectedTab(getActivity(), 0);
        } else if (v.getId() == R.id.module2_main_fragment_tab2) {
            HomeService.selectedTab(getActivity(), 1);
        }
    }
}
