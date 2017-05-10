package com.sir.app.retrofit.model.video;

import android.content.Context;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.api.video.VideoApis;
import com.sir.app.retrofit.contract.video.VideoContract;
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.model.video.bean.VideoResponse;
import com.sir.app.retrofit.net.exception.ExceptionHandle;
import com.sir.app.retrofit.net.exception.ServerException;
import com.sir.app.retrofit.net.http.HttpUtils;
import com.sir.app.retrofit.transformer.VideoErrorTransformer;
import com.sir.app.retrofit.transformer.VideoTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhuyinan on 2017/04/14
 */

public class VideoModelImpl implements VideoContract.Model {

    @Override
    public Observable<VideoRes> getHomeInfo(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadMemoryCache(false)//是否加载内存缓存数据
                .setLoadDiskCache(true)//是否加载内存缓存数据
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.video_BaseUrl)
                .builder(VideoApis.class)
                .getHomePage()
                .compose(new VideoTransformer<VideoRes>());
    }

    @Override
    public Observable<VideoRes> getVideoList(Context context, String catalogId, int pnum) {
        return HttpUtils.getInstance(context)
                .setLoadMemoryCache(false)//是否加载内存缓存数据
                .setLoadDiskCache(true)//是否加载内存缓存数据
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.video_BaseUrl)
                .builder(VideoApis.class)
                .getVideoList(catalogId, pnum)
                .compose(new VideoTransformer<VideoRes>());
    }

    @Override
    public Observable<VideoRes> getVideoInfo(Context context, String mediaId) {
        return HttpUtils.getInstance(context)
                .setLoadMemoryCache(false)//是否加载内存缓存数据
                .setLoadDiskCache(true)//是否加载内存缓存数据
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.video_BaseUrl)
                .builder(VideoApis.class)
                .getVideoInfo(mediaId)
                .compose(new VideoTransformer<VideoRes>());
    }
}