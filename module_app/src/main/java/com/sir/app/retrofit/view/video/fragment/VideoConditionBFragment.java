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
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.model.video.bean.VideoType;
import com.sir.app.retrofit.presenter.video.VideoPresenterImpl;
import com.sir.app.retrofit.view.video.activity.VideoPlayActivity;
import com.sir.app.retrofit.view.video.adapter.VideoAdapterA;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.tools.ToolAlert;

import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zhuyinan on 2017/4/17.
 */

public class VideoConditionBFragment extends BaseMvpFragment<VideoModelImpl, VideoPresenterImpl> implements VideoContract.View {

    @Bind(R.id.video_recyclerView_b)
    RecyclerView recyclerView;
    @Bind(R.id.ptr_layout_b)
    PtrClassicFrameLayout ptrLayout;
    VideoAdapterA videoAdapterA;

    private String catalogId;
    private int page = 1;

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_video_son_b;
    }

    @Override
    public void initMvpView(View view) {
        catalogId = getArguments().getString("catalogId");
    }

    @Override
    public void doBusiness(Context mContext) {
        videoAdapterA = new VideoAdapterA(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(videoAdapterA);
        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrLayout.refreshComplete();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadingData(page = page + 1);
            }
        });
        videoAdapterA.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                Object object = videoAdapterA.getItem(position);
                if (object instanceof VideoInfo) {
                    VideoInfo info = (VideoInfo) object;
                    getOperation().addParameter("mediaId", info.getDataId())
                            .addParameter("mediaPic", info.getPic())
                            .forward(VideoPlayActivity.class);
                } else if (object instanceof VideoType) {
                    VideoType info = (VideoType) object;
                    getOperation().addParameter("mediaId", info.getDataId())
                            .addParameter("mediaPic", info.getPic())
                            .forward(VideoPlayActivity.class);
                }
            }

            @Override
            public void onItemLongClick(Object o, int position) {

            }
        });
        List<VideoInfo> videoInfo = (List<VideoInfo>) getArguments().get("value");
        if (videoInfo != null) {
            videoAdapterA.addItem(videoInfo);
        }
    }

    @Override
    public void lazyFetchData() {
        loadingData(1);
    }

    @Override
    public void onFailure(String msg) {
        ptrLayout.refreshComplete();
        ToolAlert.showShort(getContext(), msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        ptrLayout.refreshComplete();
        VideoRes res = (VideoRes) object;
        setData(res.getList());
    }

    private void loadingData(int page) {
        mPresenter.getVideoList(getContext(), catalogId, page);
    }

    private void setData(List<VideoType> videoInfo) {
        videoAdapterA.addItem(videoInfo);
    }
}
