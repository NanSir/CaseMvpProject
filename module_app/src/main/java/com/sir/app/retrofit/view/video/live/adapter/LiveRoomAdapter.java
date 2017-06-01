package com.sir.app.retrofit.view.video.live.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.live.bean.LiveDetails;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;

/**
 * Created by zhuyinan on 2017/5/27.
 */

public class LiveRoomAdapter extends BaseRecyclerAdapter<LiveDetails> {

    public LiveRoomAdapter(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_live_room;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        LiveDetails  details = getItem(position);
        holder.setText(R.id.live_title,details.getTitle());
        holder.setText(R.id.live_anchor,details.getNick());

        ImageLoader.getInstance().displayImage(details.getThumb(), (ImageView) holder.getView(R.id.live_poster),getImageOptions());
    }

}
