package com.github.io.liweijie.lib2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.io.liweijie.base.BaseFragment;
import com.github.io.liweijie.base.dao.UserInfo;
import com.github.io.liweijie.base.moduleinterface.module.module4.Module4Intent;

/**
 * 作者：黎伟杰-子然 on 2017/3/26.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module2Fragment extends BaseFragment {

    private TextView name;
    private TextView age;
    private static final int REQUEST_CODE = 0x100;

    public static Module2Fragment newInstance(Object... args) {
        return new Module2Fragment();
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.module2_fragment_main, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = findViewById(R.id.module4_fragment_main_name);
        age = findViewById(R.id.module4_fragment_main_age);
        final UserInfo userInfo = new UserInfo();
        userInfo.setAge(16);
        userInfo.setName("张三");
        findViewById(R.id.module4_fragment_main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Module4Intent.launchModule4(getActivity(), REQUEST_CODE, userInfo);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            UserInfo info = (UserInfo) data.getSerializableExtra("data");
            name.setText(info.getName());
            age.setText(info.getAge()+"");

        }
    }
}
