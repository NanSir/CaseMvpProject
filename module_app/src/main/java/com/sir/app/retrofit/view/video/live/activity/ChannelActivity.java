package com.sir.app.retrofit.view.video.live.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpActivity;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.contract.live.LiveContract;
import com.sir.app.retrofit.model.live.LiveModelImpl;
import com.sir.app.retrofit.model.live.bean.LiveDetails;
import com.sir.app.retrofit.model.live.bean.LiveInfo;
import com.sir.app.retrofit.presenter.live.LivePresenterImp;
import com.sir.app.retrofit.view.video.live.adapter.LiveRoomAdapter;
import com.space.app.base.BaseRecyclerAdapter;

import butterknife.Bind;

/**
 * 直播频道
 * Created by zhuyinan on 2017/6/1.
 */

public class ChannelActivity extends BaseMvpActivity<LiveModelImpl, LivePresenterImp> implements LiveContract.View {

    @Bind(R.id.live_channel_recyclerView)
    RecyclerView liveChannelRecyclerView;
    LiveRoomAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_live_channel;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(getIntent().getStringExtra("channelTitle"));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void doBusiness(Context mContext) {
        adapter = new LiveRoomAdapter(getContext());
        liveChannelRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        liveChannelRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                LiveDetails details = adapter.getItem(position);
                Intent intent = new Intent(getContext(), RoomActivity.class);
                intent.putExtra("roomDetails", details);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(Object o, int position) {

            }
        });
        String slug = getIntent().getStringExtra("slug");
        mPresenter.getLiveInfo(getContext(), slug);
    }


    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onSuccess(int code, Object object) {
        LiveInfo liveInfo = (LiveInfo) object;
        findViewById(R.id.progress).setVisibility(View.GONE);
        adapter.addItem(liveInfo.getData());
        adapter.notifyDataSetChanged();
    }
}
