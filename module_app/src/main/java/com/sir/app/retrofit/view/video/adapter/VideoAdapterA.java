package com.sir.app.retrofit.view.video.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.video.bean.VideoInfo;
import com.sir.app.retrofit.model.video.bean.VideoType;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.willowtreeapps.spruce.animation.DefaultAnimations;

/**
 * Created by zhuyinan on 2017/4/17.
 */

public class VideoAdapterA extends BaseRecyclerAdapter {

    public VideoAdapterA(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_video_adapter_a;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        if (getItem(position) instanceof VideoInfo){
            VideoInfo info = (VideoInfo) getItem(position);
            holder.setText(R.id.video_title, info.getTitle());
            ImageLoader.getInstance().displayImage(info.getPic(), (ImageView) holder.getView(R.id.video_pic), getImageOptions());
        }else if (getItem(position) instanceof VideoType){
            VideoType  info= (VideoType) getItem(position);
            holder.setText(R.id.video_title, info.getTitle());
            ImageLoader.getInstance().displayImage(info.getPic(), (ImageView) holder.getView(R.id.video_pic), getImageOptions());
        }
    }
    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[]{DefaultAnimations.growAnimator(view, 800)};
    }
}
