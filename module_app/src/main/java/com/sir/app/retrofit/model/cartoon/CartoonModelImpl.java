package com.sir.app.retrofit.model.cartoon;

import android.content.Context;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.api.cartoon.CartoonApi;
import com.sir.app.retrofit.contract.cartoon.CartoonContract;
import com.sir.app.retrofit.model.cartoon.bean.BookReturn;
import com.sir.app.retrofit.model.cartoon.bean.ChapterReturn;
import com.sir.app.retrofit.net.http.HttpUtils;
import com.sir.app.retrofit.transformer.CartoonTransformer;

import java.util.Map;

import rx.Observable;

/**
 * Created by zhuyinan on 2017/05/02
 */

public class CartoonModelImpl implements CartoonContract.Model {

    @Override
    public Observable<BookReturn> getAllBook(Context context, Map<String, String> params) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .setLoadMemoryCache(false)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.SH_BaseUrl)
                .builder(CartoonApi.class)
                .getAllBook(params)
                .compose(new CartoonTransformer());
    }

    @Override
    public Observable<ChapterReturn> getChapterList(Context context, Map<String, String> params) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .setLoadMemoryCache(false)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.SH_BaseUrl)
                .builder(CartoonApi.class)
                .getChapterList(params)
                .compose(new CartoonTransformer());
    }

    @Override
    public Observable<ChapterReturn> getBookDetails(Context context, String id) {
        return null;
    }
}