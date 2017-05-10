package com.sir.app.retrofit.view.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.contract.news.NewsContract;
import com.sir.app.retrofit.model.news.NewsModelImpl;
import com.sir.app.retrofit.model.news.bean.NewsContent;
import com.sir.app.retrofit.presenter.news.NewsPresenterImpl;
import com.sir.app.retrofit.view.WebViewActivity;
import com.sir.app.retrofit.view.news.adapter.NewsContentAdapter;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.space.app.base.data.ACache;
import com.space.app.base.tools.ToolAlert;
import com.space.app.base.tools.ToolSnackbar;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 新闻片段
 * Created by zhuyinan on 2017/4/6.
 */

public class NewsSonFragment extends BaseMvpFragment<NewsModelImpl, NewsPresenterImpl> implements NewsContract.View {

    @Bind(R.id.news_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    String channelId;
    NewsContentAdapter adapter;
    protected int page;

    @Override
    public int bindLayout() {
        return R.layout.fragment_news_son;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {
        channelId = getArguments().getString("channelId");
    }

    @Override
    public void doBusiness(Context mContext) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsContentAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadingData(page = 1);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadingData(page = page + 1);
            }
        });

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ViewHolder>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                getOperation().addParameter("url", adapter.getItem(position).getLink()).forward(WebViewActivity.class);
            }

            @Override
            public void onItemLongClick(ViewHolder viewHolder, int position) {

            }
        });
    }

    public static NewsSonFragment newInstance(String channelId) {
        NewsSonFragment fragment = new NewsSonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("channelId", channelId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void lazyFetchData() {
        NewsContent newsContent = (NewsContent) ACache.get(getContext()).getAsObject(channelId);
        if (newsContent != null) {
            List<NewsContent.Pagebean.Contentlist> contentlist = newsContent.getPagebean().getContentlist();
            page = contentlist.size() / 20; //每页最多20条记录
            setData(contentlist);
        } else {
            loadingData(1);
        }
    }


    private void loadingData(int page) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("channelId", channelId);
        params.put("page", page + "");
        mPresenter.getChannelNews(getContext(), params);
    }

    @Override
    public void onSuccess(int code, Object object) {
        NewsContent newsContent = (NewsContent) object;
        ACache.get(getContext()).put(channelId, newsContent);
        setData(newsContent.getPagebean().getContentlist());
    }

    @Override
    public void onFailure(String msg) {
        ToolSnackbar.showShort(findViewById(R.id.view_coordinator), msg);
        ptrLayout.refreshComplete();
    }

    /**
     * 填充数据
     *
     * @param contentlist
     */
    private void setData(List<NewsContent.Pagebean.Contentlist> contentlist) {
        ptrLayout.refreshComplete();
        if (page == 1) {
            adapter.clear();
        }
        adapter.addItem(contentlist);
    }
}
