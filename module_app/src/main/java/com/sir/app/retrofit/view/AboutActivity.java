package com.sir.app.retrofit.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.net.utils.DeviceUtils;
import com.space.app.base.BaseActivity;

import butterknife.Bind;

/**
 * 关于
 * Created by zhuyinan on 2017/4/19.
 */

public class AboutActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    @Bind(R.id.movie_poster)
    KenBurnsView moviePoster;
    boolean isCollapsed = false;
    @Bind(R.id.about_app_name)
    TextView aboutAppName;
    @Bind(R.id.about_app_version)
    TextView aboutAppVersion;

    @Override
    public int bindLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void doBusiness(Context mContext) {
        aboutAppName.setText(getString(R.string.app_name));
        aboutAppVersion.setText("v" + DeviceUtils.getVersionName(getContext()));
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) { // 完全折叠状态
            if (!isCollapsed) {
                moviePoster.pause();
            }
            isCollapsed = true;
        } else { // 非折叠状态
            if (isCollapsed) {
                moviePoster.resume();
            }
            isCollapsed = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isCollapsed) {
            moviePoster.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        moviePoster.pause();
    }

}
