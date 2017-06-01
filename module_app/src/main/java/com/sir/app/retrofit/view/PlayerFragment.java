package com.sir.app.retrofit.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.util.DateUtils;
import com.sir.app.retrofit.util.PlayerUtils;
import com.space.app.base.BaseFragmentV4;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 播放器
 * Created by zhuyinan on 2017/5/25.
 */
public class PlayerFragment extends BaseFragmentV4 {

    String TAG = PlayerFragment.class.getSimpleName();
    boolean isShowToolbar = true;

    @Bind(R.id.plVideo)
    PLVideoTextureView plVideo;
    @Bind(R.id.tv_video_title)
    TextView videoTitle;
    @Bind(R.id.tv_video_duration)
    TextView videoDuration;
    @Bind(R.id.tv_video_current)
    TextView videoCurrent;
    @Bind(R.id.video_seek)
    SeekBar videoSeekBar;
    @Bind(R.id.video_state)
    ImageButton videoState;
    @Bind(R.id.iv_full_screen)
    ImageButton videoFullScreen;
    @Bind(R.id.iv_arrow_back)
    LinearLayout toolHead;
    @Bind(R.id.video_manage)
    LinearLayout toolFloor;


    public static PlayerFragment newInstance(String url, String title, String posterUrl, boolean isLive) {
        PlayerFragment droidPlayerFragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("url", url);
        args.putString("themeUrl", posterUrl);
        args.putBoolean("isLive", isLive);
        droidPlayerFragment.setArguments(args);
        return droidPlayerFragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_player;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        videoTitle.setText(getArguments().getString("title"));
        //设置显示比例
        plVideo.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        //设置播放封面
        //plVideo.setCoverView(getCoverView());
        //设置视频路径
        plVideo.setVideoPath(getArguments().getString("url"));
        AVOptions options = new AVOptions();
        if (getArguments().getBoolean("isLive")) {
            //直播
            toolFloor.setVisibility(View.GONE);
            //设置准备上监听器
            plVideo.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(PLMediaPlayer plMediaPlayer) {
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    if (plVideo != null) {
                        plVideo.start();
                    }
                }
            });
            //直播，则底层会有一些播放优化
            options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
            options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        } else {
            //在线
            options.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);
            setPlVideoPattern();
        }
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        //解码方式，codec＝1，硬解; codec=0, 软解
        options.setInteger(AVOptions.KEY_MEDIACODEC, 1);
        //准备后是否自动开始播放
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);
        plVideo.setAVOptions(options);
    }


    private void setPlVideoPattern() {
        //设置准备上监听器
        plVideo.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer) {
                findViewById(R.id.progress).setVisibility(View.GONE);
                videoDuration.setText(DateUtils.getStringMMSS(plMediaPlayer.getDuration()));
                videoSeekBar.setMax(DateUtils.getStringMM(plMediaPlayer.getDuration()));
                plVideo.start();
            }
        });
        //设置缓冲更新侦听器
        plVideo.setOnBufferingUpdateListener(new PLMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
                Log.i(TAG, "onBufferingUpdate==" + plMediaPlayer.getCurrentPosition());
                if (videoCurrent != null) {
                    videoCurrent.setText(DateUtils.getStringMMSS(plMediaPlayer.getCurrentPosition()));
                    videoSeekBar.setProgress(DateUtils.getStringMM(plMediaPlayer.getCurrentPosition()));
                }
            }
        });


        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //plVideo.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //plVideo.start();
            }

        });


        //完成监听器
        plVideo.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                Log.e("TAG", "视频播放完成");
            }
        });
        //侦听器上设置信息
        plVideo.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
                switch (what) {
                    //媒体信息缓冲开始
                    case PLMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        Log.e(TAG, "媒体信息缓冲开始");
                        break;
                    //媒体信息缓冲结束
                    case PLMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        Log.e(TAG, "媒体信息缓冲结束");
                        break;
                    case PLMediaPlayer.MEDIA_INFO_BUFFERING_BYTES_UPDATE:
                        Log.e(TAG, "显示 下载速度");
                        break;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.iv_arrow_back, R.id.iv_full_screen, R.id.video_layout, R.id.video_state})
    public void onClick_video(View view) {
        switch (view.getId()) {
            case R.id.iv_full_screen:
                setFullScreen();
                break;
            case R.id.iv_arrow_back:
                onClickBack();
                break;
            case R.id.video_layout:
                hideToolbar();
                break;
            case R.id.video_state:
                setPlVideo();
                break;
        }
    }

    //点击暂停
    private void setPlVideo() {
        if (plVideo.isPlaying()) {
            plVideo.pause();
            videoState.setImageResource(R.mipmap.ic_video_player_normal);
        } else {
            plVideo.start();
            videoState.setImageResource(R.mipmap.ic_video_pause_normal);
        }
    }

    //全屏
    private void setFullScreen() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    private void hideToolbar() {
        if (isShowToolbar) {
            PlayerUtils.hideToolbar(toolHead, getActivity(), 40);
            PlayerUtils.hideFloor(toolFloor, getActivity(), 40);
        } else {
            PlayerUtils.showToolbar(toolHead, getActivity(), 40);
            PlayerUtils.showFloor(toolFloor, getActivity(), 40);
        }
        isShowToolbar = !isShowToolbar;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //切横屏
            videoFullScreen.setImageResource(R.mipmap.ic_video_small_screen_normal);
        } else {
            //切竖屏
            videoFullScreen.setImageResource(R.mipmap.ic_video_full_screen_normal);
        }
    }

    //退出
    private void onClickBack() {
        if (getArguments().getBoolean("isLive")){
            getActivity().finish();
            return;
        }
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            getActivity().finish();
        }
    }

    private View getCoverView() {
        ImageView coverView = new ImageView(getContext());
        coverView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        coverView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Log.e(TAG, "CoverView:" + getArguments().getString("themeUrl"));
        ImageLoader.getInstance().displayImage(getArguments().getString("themeUrl"), coverView);
        return coverView;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (plVideo != null) {
            plVideo.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (plVideo != null) {
            plVideo.stopPlayback();
            plVideo.releaseSurfactexture();
        }
    }
}
