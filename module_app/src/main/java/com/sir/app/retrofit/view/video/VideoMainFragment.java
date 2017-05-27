package com.sir.app.retrofit.view.video;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.ViewPagerAdapter;
import com.sir.app.retrofit.contract.video.VideoContract;
import com.sir.app.retrofit.model.video.VideoModelImpl;
import com.sir.app.retrofit.model.video.bean.VideoInfo;
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.model.video.bean.VideoType;
import com.sir.app.retrofit.presenter.video.VideoPresenterImpl;
import com.sir.app.retrofit.view.video.fragment.VideoConditionAFragment;
import com.sir.app.retrofit.view.video.fragment.VideoConditionBFragment;
import com.sir.app.retrofit.view.video.live.LiveMainFragment;
import com.space.app.base.data.ACache;
import com.space.app.base.tools.ToolAlert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 视频直播主界面
 * Created by zhuyinan on 2017/4/14.
 */

public class VideoMainFragment extends BaseMvpFragment<VideoModelImpl, VideoPresenterImpl> implements VideoContract.View {

    @Bind(R.id.video_tabLayout)
    TabLayout tableLayout;
    @Bind(R.id.video_viewPager)
    ViewPager viewPager;
    ViewPagerAdapter adapter;


    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_video_main;
    }

    @Override
    public void initMvpView(View view) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        tableLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void doBusiness(Context mContext) {
        mPresenter.getHomeInfo(getContext());

        //获取缓存数据
//        VideoRes res = (VideoRes) ACache.get(getContext()).getAsObject("VideoHomeInfo");
//        if (res == null) {
//            mPresenter.getHomeInfo(getContext());
//        } else {
//            setData(res);
//        }
    }

    @Override
    public void onFailure(String msg) {


        ToolAlert.showShort(getContext(), msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        VideoRes res = (VideoRes) object;
        ACache.get(getContext()).put("VideoHomeInfo", res);
        setData(res);
    }

    /**
     * 填充数据
     *
     * @param res
     */
    private void setData(VideoRes res) {
        List<VideoInfo> banner = new ArrayList<>();
        Bundle bundle;
        for (VideoType videoType : res.getList()) {
            switch (videoType.getTitle()) {
                case "Banner":
                    banner = videoType.getChildList();
                    break;
                case "免费推荐":
                    break;
                case "热点资讯":
                    break;
                case "精彩推荐":
                    VideoConditionAFragment fragmentA = new VideoConditionAFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putSerializable("banner", (Serializable) banner);
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentA.setArguments(bundle);
                    adapter.addFrag(fragmentA, videoType.getTitle());
                    break;
                case "好莱坞":
                    VideoConditionBFragment fragmentB = new VideoConditionBFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentB.setArguments(bundle);
                    adapter.addFrag(fragmentB, videoType.getTitle());
                    break;
                case "专题":
                    //视频不存在
                    VideoConditionAFragment fragmentC = new VideoConditionAFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("banner", (Serializable) banner);
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentC.setArguments(bundle);
                    adapter.addFrag(fragmentC, videoType.getTitle());
                    break;
                case "直播专区":
//                    VideoConditionAFragment fragmentD = new VideoConditionAFragment();
//                    bundle = new Bundle();
//                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
//                    bundle.putSerializable("banner", (Serializable) banner);
//                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
//                    fragmentD.setArguments(bundle);
//                    adapter.addFrag(fragmentD, videoType.getTitle());
                    adapter.addFrag(new LiveMainFragment(), "直播专区");
                    break;
                case "微电影":
                    VideoConditionBFragment fragmentE = new VideoConditionBFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentE.setArguments(bundle);
                    adapter.addFrag(fragmentE, videoType.getTitle());
                    break;
                case "日韩精选":
                    VideoConditionAFragment fragmentF = new VideoConditionAFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("banner", (Serializable) banner);
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentF.setArguments(bundle);
                    adapter.addFrag(fragmentF, videoType.getTitle());
                    break;
                case "大咖剧场":
                    VideoConditionBFragment fragmentG = new VideoConditionBFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentG.setArguments(bundle);
                    adapter.addFrag(fragmentG, videoType.getTitle());
                    break;
                case "午夜剧场":
                    VideoConditionBFragment fragmentH = new VideoConditionBFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentH.setArguments(bundle);
                    adapter.addFrag(fragmentH, videoType.getTitle());
                    break;
                case "大片抢先看":
                    VideoConditionBFragment fragmentI = new VideoConditionBFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentI.setArguments(bundle);
                    adapter.addFrag(fragmentI, videoType.getTitle());
                    break;
                case "香港映象":
                    VideoConditionBFragment fragmentJ = new VideoConditionBFragment();
                    bundle = new Bundle();
                    bundle.putSerializable("value", (Serializable) videoType.getChildList());
                    bundle.putString("catalogId", getCatalogId(videoType.getMoreURL()));
                    fragmentJ.setArguments(bundle);
                    adapter.addFrag(fragmentJ, videoType.getTitle());
                    break;
            }
        }
        adapter.notifyDataSetChanged();

    }

    /**
     * 根据URL获取CatalogId
     *
     * @param url
     * @return
     */
    public String getCatalogId(String url) {
        if (!TextUtils.isEmpty(url) && url.contains("=")) {
            return url.substring(url.lastIndexOf("=") + 1);
        }
        return "";
    }
}
