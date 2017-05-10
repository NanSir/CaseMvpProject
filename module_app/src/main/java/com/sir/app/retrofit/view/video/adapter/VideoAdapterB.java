package com.sir.app.retrofit.view.video.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.video.bean.VideoInfo;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.willowtreeapps.spruce.animation.DefaultAnimations;

/**
 * Created by zhuyinan on 2017/4/18.
 */

public class VideoAdapterB extends BaseRecyclerAdapter<VideoInfo> {
    public VideoAdapterB(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_video_adapter_b;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        VideoInfo info = getItem(position);
        holder.setText(R.id.video_title, info.getTitle());
        holder.setText(R.id.video_duration, info.getDuration());
        holder.setText(R.id.video_airTime, info.getAirTime() + "年");
        holder.setText(R.id.video_introduce, "  " + info.getDescription());
        ImageLoader.getInstance().displayImage(info.getPic(), (ImageView) holder.getView(R.id.video_pic), getImageOptions());
    }
    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[]{DefaultAnimations.shrinkAnimator(view, 800)};
    }
}
