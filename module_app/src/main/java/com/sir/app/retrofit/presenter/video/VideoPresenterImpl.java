package com.sir.app.retrofit.presenter.video;

import android.content.Context;
import android.util.Log;

import com.sir.app.retrofit.contract.video.VideoContract;
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.net.callback.RxSubscriber;
import com.sir.app.retrofit.net.exception.ResponseThrowable;

/**
 * Created by zhuyinan on 2017/04/14
 */

public class VideoPresenterImpl extends VideoContract.Presenter {

    @Override
    public void getHomeInfo(Context context) {
        addSubscribe(mModel.getHomeInfo(context).subscribe(new RxSubscriber<VideoRes>() {
            @Override
            public void onSuccess(VideoRes videoRes) {
                mView.onSuccess(100, videoRes);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getVideoList(Context context, String catalogId, int pnum) {
        addSubscribe(mModel.getVideoList(context, catalogId, pnum).subscribe(new RxSubscriber<VideoRes>() {
            @Override
            public void onSuccess(VideoRes videoRes) {
                mView.onSuccess(101, videoRes);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                ex.printStackTrace();
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getVideoInfo(Context context, String mediaId) {
        addSubscribe(mModel.getVideoInfo(context, mediaId).subscribe(new RxSubscriber<VideoRes>() {
            @Override
            public void onSuccess(VideoRes videoRes) {
                mView.onSuccess(102, videoRes);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }
}