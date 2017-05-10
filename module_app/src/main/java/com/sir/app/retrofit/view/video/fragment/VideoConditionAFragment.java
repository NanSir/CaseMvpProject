package com.sir.app.retrofit.view.video.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.contract.video.VideoContract;
import com.sir.app.retrofit.model.video.VideoModelImpl;
import com.sir.app.retrofit.model.video.bean.VideoInfo;
import com.sir.app.retrofit.presenter.video.VideoPresenterImpl;
import com.sir.app.retrofit.view.video.activity.VideoPlayActivity;
import com.sir.app.retrofit.view.video.adapter.VideoAdapterA;
import com.sir.app.retrofit.view.widget.CarouselImages;
import com.space.app.base.BaseRecyclerAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zhuyinan on 2017/4/17.
 */

public class VideoConditionAFragment extends BaseMvpFragment<VideoModelImpl, VideoPresenterImpl> implements VideoContract.View {

    @Bind(R.id.video_banner_a)
    CarouselImages bannerView;
    @Bind(R.id.video_recyclerView_a)
    RecyclerView recyclerViewA;
    VideoAdapterA adapterA;
    List<VideoInfo> banners;
    List<VideoInfo> videoInfos;

    private String catalogId;

    @Override
    public int bindLayout() {
        return R.layout.fragment_video_son_a;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }


    @Override
    public void initMvpView(View view) {
        catalogId = getArguments().getString("catalogId");

    }

    @Override
    public void doBusiness(Context mContext) {
        adapterA = new VideoAdapterA(getActivity());
        recyclerViewA.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewA.setAdapter(adapterA);
        bannerView.setOnItemBannerListener(new CarouselImages.OnItemBannerListener() {
            @Override
            public void OnItemBanner(int index) {
                getOperation().addParameter("mediaPic", banners.get(index - 1).getPic())
                        .addParameter("mediaId", banners.get(index - 1).getDataId())
                        .forward(VideoPlayActivity.class);
            }
        });
        adapterA.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                VideoInfo info = (VideoInfo) adapterA.getItem(position);
                getOperation().addParameter("mediaId", info.getDataId())
                        .addParameter("mediaPic", info.getPic())
                        .forward(VideoPlayActivity.class);
            }

            @Override
            public void onItemLongClick(Object o, int position) {

            }
        });
    }


    @Override
    public void lazyFetchData() {
        banners = (List<VideoInfo>) getArguments().get("banner");
        videoInfos = (List<VideoInfo>) getArguments().get("value");
        setBanner(banners);
        setData(videoInfos);
    }

    //设置banner数据
    private void setBanner(List<VideoInfo> banners) {
        String[] imagesUrl = new String[banners.size()];
        for (int i = 0; i < banners.size(); i++) {
            imagesUrl[i] = banners.get(i).getPic();
        }
        bannerView.setImagesUrl(getContext(),imagesUrl);
    }

    //设置数据
    private void setData(List<VideoInfo> list) {
        adapterA.addItem(list);
        adapterA.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onSuccess(int code, Object object) {

    }
}
