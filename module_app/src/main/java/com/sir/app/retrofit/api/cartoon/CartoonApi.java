package com.sir.app.retrofit.api.cartoon;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.model.cartoon.bean.BookResponse;
import com.sir.app.retrofit.model.cartoon.bean.BookReturn;
import com.sir.app.retrofit.model.cartoon.bean.ChapterReturn;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 漫画
 * Created by zhuyinan on 2017/5/2.
 */

public interface CartoonApi {

    @GET(NetWorkApi.SH_GetAllBook)
    Observable<BookResponse<BookReturn>> getAllBook(@QueryMap Map<String,String> map);

    @GET(NetWorkApi.SH_GetChapterList)
    Observable<BookResponse<ChapterReturn>> getChapterList(@QueryMap Map<String,String> map);






}
