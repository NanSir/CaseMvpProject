package com.sir.app.retrofit.model.movie;

import android.content.Context;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.api.movie.MovieApi;
import com.sir.app.retrofit.contract.movie.MovieContract;
import com.sir.app.retrofit.model.movie.base.MovieData;
import com.sir.app.retrofit.net.http.HttpUtils;
import com.sir.app.retrofit.transformer.MovieTransformer;

import rx.Observable;

/**
 * Created by zhuyinan on 2017/04/11
 */

public class MovieModelImpl implements MovieContract.Model {
    @Override
    public Observable<MovieData> getRecentShadow(Context context, String city) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .setLoadMemoryCache(false)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.JH_BaseUrl)
                .builder(MovieApi.class)
                .getRecentShadow(NetWorkApi.API_KEY_JH, city)
                .compose(new MovieTransformer<MovieData>());
    }
}