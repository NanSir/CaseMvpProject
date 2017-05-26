package com.sir.app.retrofit.view.video.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpActivity;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.Constant;
import com.sir.app.retrofit.contract.video.VideoContract;
import com.sir.app.retrofit.model.CollectDevice;
import com.sir.app.retrofit.model.video.VideoModelImpl;
import com.sir.app.retrofit.model.video.bean.VideoInfo;
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.model.video.bean.VideoType;
import com.sir.app.retrofit.presenter.video.VideoPresenterImpl;
import com.sir.app.retrofit.view.PlayerFragment;
import com.sir.app.retrofit.view.video.adapter.VideoAdapterB;
import com.sir.app.retrofit.view.video.dialog.VideoIntroDialog;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.tools.ToolAlert;
import com.space.app.base.tools.ToolSnackbar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 视频播放器
 * Created by zhuyinan on 2017/4/18.
 */

public class VideoPlayActivity extends BaseMvpActivity<VideoModelImpl, VideoPresenterImpl> implements VideoContract.View {

    @Bind(R.id.video_title)
    TextView videoTitle;
    @Bind(R.id.video_protagonist)
    TextView videoProtagonist;
    @Bind(R.id.video_collect)
    CheckBox videoCollect;
    @Bind(R.id.video_recyclerView_expand)
    RecyclerView recyclerViewExpand;

    CollectDevice device;
    VideoAdapterB adapter;


    VideoIntroDialog dialog;
    String mediaId;
    VideoRes videoRes;

    @Bind(R.id.video_content)
    RelativeLayout videoContent;

    LinearLayout.LayoutParams videoContentParams;
    LinearLayout.LayoutParams MATCH_PARENT;

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initMvpView(View view) {
        //保持常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //记住大小
        videoContentParams = (LinearLayout.LayoutParams) videoContent.getLayoutParams();
        MATCH_PARENT = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        adapter = new VideoAdapterB(getContext());
        recyclerViewExpand.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewExpand.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                final VideoInfo videoInfo = adapter.getItem(position);
                dialog.showOperationMenu(getContext(), videoInfo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.video_btn_play) { //播放按钮
                            if (mediaId.equals(videoInfo.getDataId())) {
                                ToolSnackbar.showShort(findViewById(R.id.view_coordinator),
                                        getString(R.string.video_is_played));
                            } else {
                                mediaId = videoInfo.getDataId();
                                getIntent().putExtra("mediaPic", videoInfo.getPic());
                                mPresenter.getVideoInfo(getContext(), mediaId);
                                dialog.dismiss();
                            }
                        } else if (view.getId() == R.id.video_btn_brief) {//简介按钮
                            dialog.setVideoInfo(getContext(), videoInfo);
                            dialog.showVideoInfo();
                        }
                    }
                });
            }

            @Override
            public void onItemLongClick(Object o, int position) {

            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        dialog = new VideoIntroDialog(getContext());
        device = new CollectDevice();//收藏
        mediaId = getIntent().getStringExtra("mediaId");
        mPresenter.getVideoInfo(getContext(), mediaId);
        //点击收藏
        videoCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                device.operateCollect(videoRes, videoCollect.isChecked());
                ToolAlert.showLong(getContext(), videoCollect.isChecked() ? "收藏成功" : "取消收藏");
            }
        });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            videoContent.setLayoutParams(MATCH_PARENT);
        } else {
            //竖屏
            videoContent.setLayoutParams(videoContentParams);
        }
    }

    @Override
    public void onFailure(String msg) {
        new AlertDialog.Builder(getContext())
                .setTitle("错误")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void onSuccess(int code, Object object) {
        videoRes = (VideoRes) object;
        setVideoPlayer(videoRes);
        setVideoInfo(videoRes);
    }


    /**
     * 设置信息
     */
    private void setVideoInfo(VideoRes videoRes) {
        videoCollect.setChecked(device.isCollect(mediaId, Constant.TYPE_VIDEO));
        videoTitle.setText(videoRes.getTitle());
        videoProtagonist.setText(videoRes.getActors());
        dialog.setVideoInfo(getContext(), videoRes);
        for (VideoType type : videoRes.getList()) {//
            if (type.getTitle().equals("猜你喜欢")) {
                adapter.addItem(type.getChildList());
            }
        }
    }


    @OnClick(R.id.view_Intro)
    public void onClickView(View view) {
        dialog.setVideoInfo(getContext(), videoRes);
        dialog.showVideoInfo();
    }


    /**
     * 播放文件
     *
     * @param videoRes
     */
    private void setVideoPlayer(VideoRes videoRes) {
        String themeUrl;
        if (!TextUtils.isEmpty(videoRes.getPic())) {//如果没有海报图
            themeUrl = videoRes.getPic();
        } else {//用传入的
            themeUrl = getIntent().getStringExtra("mediaPic");
        }
        videoRes.setDataId(mediaId);//用于收藏数据
        videoRes.setPic(themeUrl); //用于收藏数据
        if (!TextUtils.isEmpty(videoRes.getVideoUrl())) {
            replaceFragment(R.id.player_fragment, PlayerFragment.newInstance(videoRes.getVideoUrl(), videoRes.getTitle(), themeUrl,false));
        }
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }
}
