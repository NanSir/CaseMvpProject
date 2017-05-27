package com.sir.app.retrofit.api.live;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.sir.app.retrofit.model.live.bean.LiveInfo;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
import com.sir.app.retrofit.model.live.bean.RoomInfo;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 全民直播
 * Created by zhuyinan on 2017/5/26.
 */

public interface LiveApi {

    @GET(NetWorkApi.QM_Category_List)
    Observable<List<LiveChannel>> getAllCategories();

    @GET(NetWorkApi.QM_Recommend)
    Observable<RecommendInfo> getRecommend();

    @GET(NetWorkApi.QM_Live_Info)
    Observable<LiveInfo> getLiveInfo(@Path("slug") String slug);

    @GET(NetWorkApi.QM_Room)
    Observable<RoomInfo> getRoomInfo(@Path("uid") String uid);


}
