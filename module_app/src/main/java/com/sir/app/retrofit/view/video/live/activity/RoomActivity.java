package com.sir.app.retrofit.view.video.live.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.live.bean.LiveDetails;
import com.sir.app.retrofit.view.PlayerFragment;
import com.space.app.base.BaseActivity;

/**
 * 直播房间
 * Created by zhuyinan on 2017/6/1.
 */

public class RoomActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_room;
    }

    @Override
    public void initView(View view) {
        //保持常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void doBusiness(Context mContext) {
        LiveDetails details = (LiveDetails) getIntent().getSerializableExtra("roomDetails");
        replaceFragment(R.id.live_fragment, PlayerFragment.newInstance(details.getStream(), details.getTitle(), details.getThumb(), true));
        if (details.getScreen()==0){
           setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }


}
