package com.sir.app.retrofit.view.video.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.video.bean.VideoInfo;
import com.sir.app.retrofit.model.video.bean.VideoRes;

/**
 * 视频信息简介
 * Created by zhuyinan on 2017/4/19.
 */

public class VideoIntroDialog {

    BottomSheetDialog dialog;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    public VideoIntroDialog(Context context) {
        dialog = new BottomSheetDialog(context);
        builder = new AlertDialog.Builder(context);
    }

    public void showOperationMenu(Context context, VideoInfo VideoInfo, View.OnClickListener listener) {
        View contentView = View.inflate(context, R.layout.item_centent_sheets, null);
        setText(contentView, R.id.video_title, VideoInfo.getTitle());
        setText(contentView, R.id.video_duration, VideoInfo.getDuration());
        builder.setView(contentView);
        alertDialog = builder.show();
        contentView.findViewById(R.id.video_btn_brief).setOnClickListener(listener);
        contentView.findViewById(R.id.video_btn_play).setOnClickListener(listener);
        contentView.findViewById(R.id.ibtn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    public void showVideoInfo() {
        dialog.show();
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

    /**
     * 显示video信息 Object is VideoRes or VideoInfo
     *
     * @param context
     * @param info
     */
    public void setVideoInfo(Context context, Object info) {
        View contentView = View.inflate(context, R.layout.item_bottom_sheets, null);
        if (info instanceof VideoRes) {
            VideoRes videoRes = (VideoRes) info;

            contentView.findViewById(R.id.movie_director_layout).setVisibility(View.VISIBLE);
            contentView.findViewById(R.id.movie_type_layout).setVisibility(View.VISIBLE);
            contentView.findViewById(R.id.movie_starring_layout).setVisibility(View.VISIBLE);

            setText(contentView, R.id.video_title, videoRes.getTitle());
            setText(contentView, R.id.video_duration, videoRes.getDuration());
            setText(contentView, R.id.video_duration, videoRes.getDuration());
            setText(contentView, R.id.video_starring, videoRes.getActors());
            setText(contentView, R.id.video_type, videoRes.getVideoType());
            setText(contentView, R.id.video_release_info, videoRes.getDescription());
        } else if (info instanceof VideoInfo) {
            VideoInfo videoInfo = (VideoInfo) info;
            //隐藏没有数据的控件
            contentView.findViewById(R.id.movie_director_layout).setVisibility(View.GONE);
            contentView.findViewById(R.id.movie_type_layout).setVisibility(View.GONE);
            contentView.findViewById(R.id.movie_starring_layout).setVisibility(View.GONE);

            setText(contentView, R.id.video_title, videoInfo.getTitle());
            setText(contentView, R.id.video_duration, videoInfo.getDuration());
            setText(contentView, R.id.video_release_info, videoInfo.getDescription());
        }
        dialog.setContentView(contentView);
        contentView.findViewById(R.id.rlayout_intro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        final BottomSheetBehavior behavior = BottomSheetBehavior.from((View) contentView.getParent());
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                // 在使用BottomSheetDialog滑动消失后，再次点击显示的时候
                // 没有成功显示，BottomSheetBehavior被设置成了隐藏模式
                // 需要在dismiss()的监听中重新设置下状态就可以了
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    private void setText(View view, int id, String value) {
        TextView tv = (TextView) view.findViewById(id);
        tv.setText(value);
    }
}
