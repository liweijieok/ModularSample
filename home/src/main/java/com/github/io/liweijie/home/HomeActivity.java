package com.github.io.liweijie.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.io.liweijie.base.BaseActivity;
import com.github.io.liweijie.base.dao.UserInfo;
import com.github.io.liweijie.base.moduleinterface.module.module1.Module1Service;
import com.github.io.liweijie.base.moduleinterface.module.module2.Module2Service;
import com.github.io.liweijie.base.moduleinterface.module.module3.Module3Service;
import com.github.io.liweijie.base.moduleinterface.module.module5.Module5Intent;
import com.github.io.liweijie.base.moduleinterface.provider.IHomeProvider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule1Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule2Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule3Provider;
import com.github.io.liweijie.base.moduleinterface.provider.IModule5Provider;
import com.github.io.liweijie.base.moduleinterface.router.ModuleManager;
import com.github.io.liweijie.base.util.LG;

import java.util.HashMap;
import java.util.Map;

@Route(path = IHomeProvider.HOME_ACT_HOME)
public class HomeActivity extends BaseActivity {

    private int currentPosition = -1;
    private Map<String, Fragment> mFragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mFragments = new HashMap<>();
        selectedTab(getIntent().getIntExtra(IHomeProvider.HOME_TABTYPE, 0));
    }


    public void onClickTab(View view) {
        int id = view.getId();
        if (id == R.id.home_tab_module1) {
            selectedTab(0);
        } else if (id == R.id.home_tab_module2) {
            selectedTab(1);
        } else if (id == R.id.home_tab_module3) {
            selectedTab(2);
        }
    }

    public void selectedTab(int position) {
        if (currentPosition == position)
            return;
        switch (position) {
            case 0:
                selectFragment(IModule1Provider.MODULE1_KEY_FRAGMENT);
                break;
            case 1:
                selectFragment(IModule2Provider.MODULE2_KEY_FRAGMENT);
                break;
            case 2:
                //
                if (ModuleManager.getInstance().hasModule(IModule3Provider.MODULE3_MAIN_SERVICE)) {
                    selectFragment(IModule3Provider.MODULE3_KEY_FRAGMENT);
                } else if (ModuleManager.getInstance().hasModule(IModule5Provider.MODULE5_MAIN_SERVICE)) {
                    Module5Intent.launchModule5(new UserInfo("王五", 18));
                }
                break;
        }
        currentPosition = position;
    }

    private void selectFragment(String key) {
        Fragment fragment;
        fragment = mFragments.get(key);
        String msg;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(beginTransaction);
        if (fragment == null) {
            if (IModule1Provider.MODULE1_KEY_FRAGMENT.equals(key)) {
                fragment = Module1Service.getModule1Frgment();
                msg = "module1模块";
            } else if (IModule2Provider.MODULE2_KEY_FRAGMENT.equals(key)) {
                fragment = Module2Service.getModule2Fragment();
                msg = "modul2模块";
            } else if (IModule3Provider.MODULE3_KEY_FRAGMENT.equals(key)) {
                fragment = Module3Service.getModule2Fragment();
                msg = "module3模块";
            } else {
                return;
            }
            if (fragment == null) {
                if (LG.isDebug) {
                    Toast.makeText(this, "没有" + msg, Toast.LENGTH_SHORT).show();
                }
                beginTransaction.commit();
                return;
            }
            mFragments.put(key, fragment);
            beginTransaction.add(R.id.home_content_fragment, fragment);
        } else {
            beginTransaction.show(fragment);
        }
        beginTransaction.commit();
    }

    private void hideFragment(FragmentTransaction beginTransaction) {
        for (Map.Entry<String, Fragment> item : mFragments.entrySet()) {
            beginTransaction.hide(item.getValue());
        }
    }

    //一般而言需要这样子传递，Activity不涉及到具体逻辑处理，除非是全局性的
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Map.Entry<String, Fragment> item : mFragments.entrySet()) {
            Fragment fragment = item.getValue();
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
