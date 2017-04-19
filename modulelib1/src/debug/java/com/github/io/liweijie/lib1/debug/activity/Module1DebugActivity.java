package com.github.io.liweijie.lib1.debug.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.io.liweijie.base.BaseActivity;
import com.github.io.liweijie.base.moduleinterface.module.module1.Module1Service;
import com.github.io.liweijie.lib1.R;

/**
 * 作者：黎伟杰-子然 on 2017/4/19.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Module1DebugActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1_debug_activity);
        Fragment module1 = Module1Service.getModule1Frgment();
        getSupportFragmentManager().beginTransaction().add(R.id.moddule1_debug_content, module1)
                .commitAllowingStateLoss();
    }
}
