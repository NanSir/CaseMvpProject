package com.sir.app.retrofit.model.news;

import android.content.Context;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.api.news.NewsApi;
import com.sir.app.retrofit.contract.news.NewsContract;
import com.sir.app.retrofit.model.news.bean.NewsCityList;
import com.sir.app.retrofit.model.news.bean.NewsChannelList;
import com.sir.app.retrofit.model.news.bean.NewsContent;
import com.sir.app.retrofit.net.http.HttpUtils;
import com.sir.app.retrofit.net.transformer.DefaultTransformer;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;

/**
 * 新闻M
 * Created by zhuyinan on 2017/04/01
 */

public class NewsModelImpl implements NewsContract.Model {
    @Override
    public Observable<NewsChannelList> getNewsChannelList(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadMemoryCache(false)//是否加载内存缓存数据
                .setLoadDiskCache(true)//是否加载内存缓存数据
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.YH_BaseUrl)
                .builder(NewsApi.class)
                .getNewsChannelList(NetWorkApi.APP_ID_YY, NetWorkApi.API_KEY_YY)
                .compose(new DefaultTransformer());// 进行预处理
    }

    @Override
    public Observable<NewsCityList> getCityList(Context context) {
        return HttpUtils.getInstance(context)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.YH_BaseUrl)
                .builder(NewsApi.class)
                .getCityList(NetWorkApi.APP_ID_YY, NetWorkApi.API_KEY_YY)
                .compose(new DefaultTransformer<NewsCityList>());
    }

    @Override
    public Observable<NewsContent> getChannelNews(Context context, Map<String, String> params) {
        params.put("showapi_appid",NetWorkApi.APP_ID_YY);
        params.put("showapi_sign",NetWorkApi.API_KEY_YY);
        return HttpUtils.getInstance(context)
                .setLoadMemoryCache(false)//是否加载内存缓存数据
                .setLoadDiskCache(true)//是否加载内存缓存数据
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.YH_BaseUrl)
                .builder(NewsApi.class)
                .getChannelNews(params)
                .compose(new DefaultTransformer<NewsContent>());
    }


}