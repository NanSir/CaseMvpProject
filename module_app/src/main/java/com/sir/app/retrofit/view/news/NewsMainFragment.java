package com.sir.app.retrofit.view.news;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.ViewPagerAdapter;
import com.sir.app.retrofit.contract.news.NewsContract;
import com.sir.app.retrofit.model.news.NewsModelImpl;
import com.sir.app.retrofit.model.news.bean.NewsChannelList;
import com.sir.app.retrofit.presenter.news.NewsPresenterImpl;
import com.sir.app.retrofit.view.news.fragment.NewsSonFragment;
import com.space.app.base.data.ACache;
import com.space.app.base.tools.ToolAlert;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;

/**
 * 新闻板块主界面
 * Created by zhuyinan on 2017/4/1.
 */

public class NewsMainFragment extends BaseMvpFragment<NewsModelImpl, NewsPresenterImpl> implements NewsContract.View {

    @Bind(R.id.news_tabLayout)
    TabLayout tableLayout;
    @Bind(R.id.news_viewPager)
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    int pageLimit = 6;

    @Override
    public int bindLayout() {
        return R.layout.fragment_news_main;
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
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(pageLimit);
        tableLayout.setupWithViewPager(viewPager);

        //获取缓存数据
        List<NewsChannelList.Channel> channels =
                (List<NewsChannelList.Channel>) ACache.get(getContext()).getAsObject("channels");
        if (channels != null && channels.size() > 0) {
            setData(channels);
        } else {
            mPresenter.getNewsChannelList(getContext());
        }
    }

    @Override
    public void onFailure(String msg) {
        ToolAlert.showShort(getContext(), "服务器异常："+msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        NewsChannelList channelList = (NewsChannelList) object;
        setData(channelList.getChannelList());
    }

    /**
     * 填充数据
     * @param channels
     */
    private void setData(List<NewsChannelList.Channel> channels) {
        for (int i = 0; i < pageLimit; i++) {
            adapter.addFrag(NewsSonFragment.newInstance(channels.get(i).getChannelId()), channels.get(i).getName());
        }
        adapter.notifyDataSetChanged();
        //缓存数据
        ACache.get(getContext()).put("channels", (Serializable) channels);
    }
}
