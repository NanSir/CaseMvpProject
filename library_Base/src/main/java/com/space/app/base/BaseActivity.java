package com.space.app.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sir.app.autolayout.AutoLayoutActivity;
import com.space.app.base.config.SysKey;
import com.space.app.base.utils.SPUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Activity基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseActivity extends AutoLayoutActivity implements IBaseActivity {

    private BaseApplication mApplication = null;

    private WeakReference<Activity> context = null;

    private Operation mBaseOperation = null;

    protected List<Fragment> mFragments;

    protected int upPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage();

        setContentView(bindLayout());

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            //mFragments = getSupportFragmentManager().getFragments();
            upPosition = savedInstanceState.getInt("Fragment");
        }
        mFragments = new ArrayList<>();

        mApplication = (BaseApplication) getApplicationContext();

        context = new WeakReference<Activity>(this);

        mApplication.pushTask(context);

        mBaseOperation = new Operation(this);

        initView(getWindow().getDecorView());

        doBusiness(this);
    }


    /**
     * 多个Fragment切换不重新实例化
     *
     * @param position
     */
    protected void setShowFragment(int layoutId, int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!mFragments.get(position).isAdded()) {
            transaction.add(layoutId, mFragments.get(position));
        }
        transaction.show(mFragments.get(position));
        if (upPosition != position) {
            transaction.hide(mFragments.get(upPosition));
        }
        transaction.commit();
        upPosition = position;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.removeTask(context);
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getContext() {
        if (null != context) {
            return context.get();
        } else {
            return null;
        }
    }

    /**
     * 获取共通操作机能
     */
    protected Operation getOperation() {
        return this.mBaseOperation;
    }

    /**
     * 整个应用Applicaiton
     */
    protected BaseApplication getMyApplication() {
        return this.mApplication;
    }

    /**
     * Actionbar点击返回键关闭事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setLanguage() {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = Locale.getDefault();
        String langStr = (String) SPUtils.get(this, SysKey.CONFIG_LANGUAGE, "zh");
        //TODO 获取Sharedpreferences中存储的app语言环境
        if ("zh".equals(langStr)) {
            locale = Locale.CHINA;
        } else if ("en".equals(langStr)) {
            locale = Locale.ENGLISH;
        }
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    protected void setTranslucentTheme() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Fragment", upPosition);
    }
}
