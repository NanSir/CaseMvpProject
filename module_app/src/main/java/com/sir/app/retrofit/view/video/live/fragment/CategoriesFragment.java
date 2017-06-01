package com.sir.app.retrofit.view.video.live.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.sir.app.retrofit.view.video.live.activity.ChannelActivity;
import com.sir.app.retrofit.view.video.live.adapter.CategoriesAdapter;
import com.space.app.base.BaseFragmentV4;
import com.space.app.base.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;

/**
 * 直播类型
 * Created by zhuyinan on 2017/5/26.
 */
public class CategoriesFragment extends BaseFragmentV4 {

    @Bind(R.id.live_categories_recyclerView)
    RecyclerView recyclerView;

    CategoriesAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_live_categories;
    }

    @Override
    public void initView(View view) {
        adapter = new CategoriesAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                LiveChannel channel = adapter.getItem(position);
                getOperation().addParameter("slug", channel.getSlug())
                        .addParameter("channelTitle", channel.getName())
                        .forward(ChannelActivity.class);
            }

            @Override
            public void onItemLongClick(Object o, int position) {

            }
        });

    }

    @Override
    public void lazyFetchData() {
        List<LiveChannel> channels = (List<LiveChannel>) getArguments().get("channel");
        adapter.addItem(channels);
        adapter.notifyDataSetChanged();
    }

    public static CategoriesFragment newInstance(List<LiveChannel> channels) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putSerializable("channel", (Serializable) channels);
        fragment.setArguments(args);
        return fragment;
    }


}
