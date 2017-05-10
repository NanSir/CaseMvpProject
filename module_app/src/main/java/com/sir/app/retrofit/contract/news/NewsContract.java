package com.sir.app.retrofit.contract.news;

import android.content.Context;

import com.sir.app.retrofit.base.BaseModel;
import com.sir.app.retrofit.base.BasePresenter;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.model.news.bean.NewsCityList;
import com.sir.app.retrofit.model.news.bean.NewsChannelList;
import com.sir.app.retrofit.model.news.bean.NewsContent;

import java.util.Map;

import rx.Observable;

/**
 * Created by zhuyinan on 2017/4/1.
 */

public interface NewsContract {

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getNewsChannelList(Context context);

        public abstract void getCityList(Context context);

        public abstract void getChannelNews(Context context, Map<String, String> map);
    }

    interface Model extends BaseModel {
        Observable<NewsChannelList> getNewsChannelList(Context context);

        Observable<NewsCityList> getCityList(Context context);

        Observable<NewsContent> getChannelNews(Context context, Map<String, String> map);
    }


}