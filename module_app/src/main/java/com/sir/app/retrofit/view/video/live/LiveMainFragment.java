package com.sir.app.retrofit.view.video.live;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.ViewPagerAdapter;
import com.sir.app.retrofit.contract.live.LiveContract;
import com.sir.app.retrofit.model.live.LiveModelImpl;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
import com.sir.app.retrofit.presenter.live.LivePresenterImp;
import com.sir.app.retrofit.view.video.live.adapter.LiveRecommendAdapter;
import com.sir.app.retrofit.view.video.live.fragment.CategoriesFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;

/**
 * 直播专区
 * Created by zhuyinan on 2017/5/26.
 */
public class LiveMainFragment extends BaseMvpFragment<LiveModelImpl, LivePresenterImp> implements LiveContract.View {

    @Bind(R.id.live_categories_viewPage)
    ViewPager viewPager;
    ViewPagerAdapter menuAdapter;

    @Bind(R.id.live_recyclerView)
    RecyclerView LiveRecyclerView;
    LiveRecommendAdapter recommendAdapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_live_main;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        menuAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(menuAdapter);
        recommendAdapter = new LiveRecommendAdapter(getActivity());
        LiveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LiveRecyclerView.setAdapter(recommendAdapter);
    }

    @Override
    public void lazyFetchData() {
        //获取类别
        mPresenter.getAllCategories(getContext());
        //获取经推荐
        mPresenter.getRecommend(getContext());
    }

    @Override
    public void onFailure(String msg) {
        Log.e("TAG", msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        if (code == 100) {
            List<LiveChannel> liveChannels = (List<LiveChannel>) object;
            setMenu(liveChannels);
        } else if (code == 101) {
            findViewById(R.id.progress).setVisibility(View.GONE);
            RecommendInfo recommendInfo = (RecommendInfo) object;
            recommendAdapter.addItem(recommendInfo.getRoom());
            recommendAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 菜单分组
     *
     * @param liveChannels
     */
    private void setMenu(List<LiveChannel> liveChannels) {
        int number = 10;
        int part = liveChannels.size() / number;
        for (int i = 0; i < part; i++) {
            List<LiveChannel> channels = new ArrayList<>();
            channels.addAll(liveChannels.subList(0, number));
            menuAdapter.addFrag(CategoriesFragment.newInstance(channels), "menu");
            liveChannels.subList(0, number).clear();
        }
        if (!liveChannels.isEmpty()) {
            menuAdapter.addFrag(CategoriesFragment.newInstance(liveChannels), "menu");
        }
        menuAdapter.notifyDataSetChanged();
    }
}
