package com.sir.app.retrofit.api.video;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.model.video.bean.VideoResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 视频资源
 * Created by zhuyinan on 2017/4/14.
 */

public interface VideoApis {

    /**
     * 首页
     *
     * @return
     */
    @GET(NetWorkApi.video_home)
    Observable<VideoResponse<VideoRes>> getHomePage();


    /**
     * 视频频道
     *
     * @param catalogId 402834815584e463015584e53843000b
     * @param pnum
     * @return
     */
    @GET(NetWorkApi.video_colums)
    Observable<VideoResponse<VideoRes>> getVideoList(@Query("catalogId") String catalogId, @Query("pnum") int pnum);


    /**
     * 影片详情
     *
     * @param mediaId 影片id
     * @return
     */
    @GET(NetWorkApi.video_detail)
    Observable<VideoResponse<VideoRes>> getVideoInfo(@Query("mediaId") String mediaId);


    /**
     * 影片搜索
     *
     * @param pnum
     * @return
     */
    @GET(NetWorkApi.video_search)
    Observable<VideoResponse<VideoRes>> getVideoListByKeyWord(@Query("keyword") String keyword, @Query("pnum") String pnum);

    /**
     * 获取评论列表
     *
     * @param mediaId
     * @param pnum
     * @return
     */
    @GET(NetWorkApi.video_commentary)
    Observable<VideoResponse<VideoRes>> getCommentList(@Query("mediaId") String mediaId, @Query("pnum") String pnum);
}
