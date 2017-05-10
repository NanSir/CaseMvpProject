package com.sir.app.retrofit.presenter.news;

import android.content.Context;

import com.sir.app.retrofit.contract.news.NewsContract;
import com.sir.app.retrofit.model.news.bean.NewsCityList;
import com.sir.app.retrofit.model.news.bean.NewsChannelList;
import com.sir.app.retrofit.model.news.bean.NewsContent;
import com.sir.app.retrofit.net.callback.RxSubscriber;
import com.sir.app.retrofit.net.exception.ResponseThrowable;

import java.util.Map;

/**
 * 新闻类
 * Created by zhuyinan on 2017/04/01
 */

public class NewsPresenterImpl extends NewsContract.Presenter {

    @Override
    public void getNewsChannelList(Context context) {
        addSubscribe(mModel.getNewsChannelList(context).subscribe(new RxSubscriber<NewsChannelList>() {
            @Override
            public void onSuccess(NewsChannelList newsChannelList) {
                mView.onSuccess(100, newsChannelList);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getCityList(Context context) {
        addSubscribe(mModel.getCityList(context).subscribe(new RxSubscriber<NewsCityList>() {
            @Override
            public void onSuccess(NewsCityList cityList) {
                mView.onSuccess(101, cityList);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getChannelNews(Context context, Map<String, String> params) {
        addSubscribe(mModel.getChannelNews(context, params).subscribe(new RxSubscriber<NewsContent>() {
            @Override
            public void onSuccess(NewsContent newsContent) {
                mView.onSuccess(102, newsContent);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }


}