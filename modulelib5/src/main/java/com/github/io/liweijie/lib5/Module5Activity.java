package com.github.io.liweijie.lib5;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.io.liweijie.base.BaseActivity;
import com.github.io.liweijie.base.dao.UserInfo;
import com.github.io.liweijie.base.moduleinterface.provider.IModule5Provider;

/**
 * 作者：黎伟杰-子然 on 2017/4/18.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
@Route(path = IModule5Provider.MODULE5_ACT_MAIN)
public class Module5Activity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module5_activity_main);
        TextView textView = (TextView) findViewById(R.id.module5_activity_main_tv);
        UserInfo info = (UserInfo) getIntent().getSerializableExtra("data");
        if (info!=null) {
            textView.setText("从别的模块传递过来的数据\n" + "姓名是:" + info.getName() + "\n年龄是：" + info.getAge());
        }
    }
}
