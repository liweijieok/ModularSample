package com.github.io.liweijie.home;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.io.liweijie.base.moduleinterface.module.home.HomeIntent;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;

@Route(path = IHomeProvider.HOME_ACT_SPLASH)
public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler = new Handler(getMainLooper());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeIntent.launchHome(2);
                finish();
            }
        }, 1500);
    }
}
