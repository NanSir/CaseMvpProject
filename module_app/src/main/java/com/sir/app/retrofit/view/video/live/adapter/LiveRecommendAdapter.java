package com.sir.app.retrofit.view.video.live.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.live.bean.LiveDetails;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
import com.sir.app.retrofit.view.video.live.activity.RoomActivity;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;

/**
 * Created by zhuyinan on 2017/5/27.
 */

public class LiveRecommendAdapter extends BaseRecyclerAdapter<RecommendInfo.Room> {

    LiveRoomAdapter adapter;
    RecyclerView recyclerView;

    public LiveRecommendAdapter(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_live_recommend;
    }

    @Override
    public void onBindHolder(ViewHolder holder, final int position) {
        final RecommendInfo.Room room = this.getItem(position);
        if (room.getDetails().size() > 0) {
            holder.getView(R.id.rec_title_layout).setVisibility(View.VISIBLE);
            holder.setText(R.id.rec_title, room.getName());
            ImageLoader.getInstance().displayImage(room.getIcon(), (ImageView) holder.getView(R.id.rec_icon_a));
            ImageLoader.getInstance().displayImage(room.getIcon(), (ImageView) holder.getView(R.id.rec_icon_b));
            adapter = new LiveRoomAdapter(mContext);
            adapter.addItem(room.getDetails());
            recyclerView = holder.getView(R.id.live_recommend_recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(Object o, int pos) {
                    LiveDetails details = room.getDetails().get(pos);
                    Intent intent = new Intent(mContext, RoomActivity.class);
                    intent.putExtra("roomDetails",details);
                    mContext.startActivity(intent);
                }

                @Override
                public void onItemLongClick(Object o, int position) {

                }
            });
        } else {
            holder.getView(R.id.rec_title_layout).setVisibility(View.GONE);
        }
    }
}
