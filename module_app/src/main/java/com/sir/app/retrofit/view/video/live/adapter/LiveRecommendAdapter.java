package com.sir.app.retrofit.view.video.live.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
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
    public void onBindHolder(ViewHolder holder, int position) {
        RecommendInfo.Room room = getItem(position);
        holder.setText(R.id.rec_title, room.getName());
        ImageLoader.getInstance().displayImage(room.getIcon(), (ImageView) holder.getView(R.id.rec_icon_a));
        adapter = new LiveRoomAdapter(mContext);
        adapter.addItem(room.getDetails());
        recyclerView = holder.getView(R.id.live_recommend_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(adapter);
    }
}
