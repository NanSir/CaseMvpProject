package com.sir.app.retrofit.api.movie;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.model.movie.base.MovieData;
import com.sir.app.retrofit.model.movie.base.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 电影列表接口()
 * Created by zhuyinan on 2017/4/6.
 */

public interface MovieApi {

    @GET(NetWorkApi.getRecentShadow)
    Observable<MovieResponse<MovieData>> getRecentShadow(@Query("key") String key, @Query("city") String city);
}
