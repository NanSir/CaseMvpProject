package com.sir.app.retrofit.view.video.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
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
import com.sir.app.retrofit.view.video.adapter.VideoAdapterB;
import com.sir.app.retrofit.view.video.dialog.VideoIntroDialog;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.tools.ToolAlert;
import com.space.app.base.tools.ToolSnackbar;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

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

    @Bind(R.id.video_videoPlayer)
    JCVideoPlayerStandard videoPlayer;
    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;
    SensorManager sensorManager;
    VideoIntroDialog dialog;
    String mediaId;
    VideoRes videoRes;

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
        videoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.backButton.setVisibility(View.INVISIBLE);
        videoPlayer.tinyBackImageView.setVisibility(View.INVISIBLE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

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
        videoPlayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (JCVideoPlayer.backPress()) {
                    return;
                } else {
                    finish();
                }
            }
        });

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
        String thumUrl;
        if (!TextUtils.isEmpty(videoRes.getPic())) {//如果没有海报图
            thumUrl = videoRes.getPic();
        } else {//用传入的
            thumUrl = getIntent().getStringExtra("mediaPic");
        }
        videoRes.setDataId(mediaId);//用于收藏数据
        if (!thumUrl.isEmpty()) {
            videoRes.setPic(thumUrl); //用于收藏数据
            ImageLoader.getInstance().displayImage(thumUrl,
                    videoPlayer.thumbImageView,
                    getMyApplication().getImageOptions());
        }
        if (!TextUtils.isEmpty(videoRes.getVideoUrl())) {
            videoPlayer.setUp(videoRes.getVideoUrl(),//设置视频URL
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoRes.getTitle());
            videoPlayer.startButton.performClick();//开始播放
            videoPlayer.backButton.setVisibility(View.VISIBLE);
            videoPlayer.titleTextView.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}
