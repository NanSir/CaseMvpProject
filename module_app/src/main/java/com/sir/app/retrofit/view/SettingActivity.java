package com.sir.app.retrofit.view;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sir.app.retrofit.R;
import com.space.app.base.BaseActivity;

/**
 * Created by zhuyinan on 2017/4/19.
 */

public class SettingActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void doBusiness(Context mContext) {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_setting, new FragmentSetting())
                .commit();
    }
}