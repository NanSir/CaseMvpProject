package com.sir.app.retrofit.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.common.UpdateAction;
import com.sir.app.retrofit.util.TabEntity;
import com.sir.app.retrofit.view.cartoon.CartoonMainFragment;
import com.sir.app.retrofit.view.movie.MovieMainFragment;
import com.sir.app.retrofit.view.news.NewsMainFragment;
import com.sir.app.retrofit.view.video.VideoMainFragment;
import com.sir.app.tablayout.CommonTabLayout;
import com.sir.app.tablayout.listener.CustomTabEntity;
import com.sir.app.tablayout.listener.OnTabSelectListener;
import com.space.app.base.BaseActivity;
import com.space.app.base.tools.ToolAlert;

import java.util.ArrayList;

import butterknife.Bind;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 工程主界面
 * Created by zhuyinan on 2017/3/28.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    @Bind(R.id.main_tabLayout)
    CommonTabLayout tabLayout;
    ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    int[] mTitles = {
            R.string.video, R.string.shadow,
            R.string.cartoon, R.string.news};
    int[] mTitles_son = {
            R.string.live_video, R.string.movie_information,
            R.string.Animated_cartoon, R.string.news_information};
    int[] mIconUncheckIds = {
            R.drawable.svg_ic_item_video_normal, R.drawable.svg_ic_item_moive_normal,
            R.drawable.svg_ic_item_anime_normal, R.drawable.svg_ic_item_news_normal};
    int[] mIconSelectIds = {
            R.drawable.svg_ic_item_video_selected, R.drawable.svg_ic_item_moive_selected,
            R.drawable.svg_ic_item_anime_selected, R.drawable.svg_ic_item_news_selected};

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(getString(mTitles_son[upPosition]));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View drawerHeader = navigationView.inflateHeaderView(R.layout.nav_header_main);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new UpdateAction(getContext()).startUpdates();
            }
        }, 3000);
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void doBusiness(Context mContext) {
        mFragments.add(new VideoMainFragment());
        mFragments.add(new MovieMainFragment());
        mFragments.add(new CartoonMainFragment());
        mFragments.add(new NewsMainFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(getString(mTitles[i]), mIconSelectIds[i], mIconUncheckIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        tabLayout.setCurrentTab(upPosition);
        setShowFragment(R.id.main_fragment, upPosition);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                toolbarTitle.setText(getString(mTitles_son[position]));
                setShowFragment(R.id.main_fragment, position);
                getMyApplication().onClearMemoryClick();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private long waitTime = 2000;
    private long touchTime = 0;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                ToolAlert.showShort(getContext(), getString(R.string.hint_out));
                touchTime = currentTime;
            } else {
                finish();
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_setting) {
            getOperation().forward(SettingActivity.class);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_out_description));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        } else if (id == R.id.nav_about) {
            getOperation().forward(AboutActivity.class);
        } else if (id == R.id.nav_out) {
            showOutHint();
        } else if (id == R.id.nav_collect) {
            getOperation().forward(CollectActivity.class);
        } else if (id == R.id.nav_skin) {

        } else {
            ToolAlert.showShort(getContext(), "正在开发");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showOutHint() {
        new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("退出应用程序")
                .setCancelText("取消")
                .setConfirmText("退出")
                .setCustomImage(R.drawable.svg_ic_out)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        finish();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                }).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.gc();  //提醒系统及时回收
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMyApplication().removeAll();
        //  System.exit(0);//正常退出App
    }


    @Override
    public void recreate() {
        super.recreate();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            if (mFragments.get(i).isAdded()) {
                transaction.remove(mFragments.get(i));
            }
        }
        transaction.commit();
    }
}
