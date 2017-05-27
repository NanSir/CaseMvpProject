package com.sir.app.retrofit.model.live;

import android.content.Context;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.api.live.LiveApi;
import com.sir.app.retrofit.contract.live.LiveContract;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.sir.app.retrofit.model.live.bean.LiveInfo;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
import com.sir.app.retrofit.model.live.bean.RoomInfo;
import com.sir.app.retrofit.net.http.HttpUtils;
import com.sir.app.retrofit.transformer.CommonTransformer;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhuyinan on 2017/5/26.
 */

public class LiveModelImpl implements LiveContract.Model {

    @Override
    public Observable<List<LiveChannel>> getAllCategories(Context context) {
        return HttpUtils.getInstance(context)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.QM_BaseUrl)
                .builder(LiveApi.class)
                .getAllCategories()
                .compose(new CommonTransformer<List<LiveChannel>>());

    }

    @Override
    public Observable<RecommendInfo> getRecommend(Context context) {
        return HttpUtils.getInstance(context)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.QM_BaseUrl)
                .builder(LiveApi.class)
                .getRecommend()
                .compose(new CommonTransformer<RecommendInfo>());
    }

    @Override
    public Observable<LiveInfo> getLiveInfo(Context context, @Path("slug") String slug) {
        return HttpUtils.getInstance(context)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.QM_BaseUrl)
                .builder(LiveApi.class)
                .getLiveInfo(slug)
                .compose(new CommonTransformer<LiveInfo>());
    }

    @Override
    public Observable<RoomInfo> getRoomInfo(Context context, @Path("uid") String uid) {
        return HttpUtils.getInstance(context)
                .getRetorfitClinet()
                .setBaseUrl(NetWorkApi.QM_BaseUrl)
                .builder(LiveApi.class)
                .getRoomInfo(uid)
                .compose(new CommonTransformer<RoomInfo>());
    }
}
