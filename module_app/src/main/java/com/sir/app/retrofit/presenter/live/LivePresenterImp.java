package com.sir.app.retrofit.presenter.live;

import android.content.Context;

import com.sir.app.retrofit.contract.live.LiveContract;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.sir.app.retrofit.model.live.bean.LiveInfo;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
import com.sir.app.retrofit.net.callback.RxSubscriber;
import com.sir.app.retrofit.net.exception.ResponseThrowable;

import java.util.List;

/**
 * Created by zhuyinan on 2017/5/26.
 */

public class LivePresenterImp extends LiveContract.Presenter {

    @Override
    public void getAllCategories(Context context) {
        addSubscribe(mModel.getAllCategories(context).subscribe(new RxSubscriber<List<LiveChannel>>() {
            @Override
            public void onSuccess(List<LiveChannel> liveChannels) {
                mView.onSuccess(100, liveChannels);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getRecommend(Context context) {
        addSubscribe(mModel.getRecommend(context).subscribe(new RxSubscriber<RecommendInfo>() {
            @Override
            public void onSuccess(RecommendInfo recommendInfo) {
                mView.onSuccess(101, recommendInfo);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getLiveInfo(Context context,String slug) {
        addSubscribe(mModel.getLiveInfo(context,slug).subscribe(new RxSubscriber<LiveInfo>() {
            @Override
            public void onSuccess(LiveInfo liveInfo) {
                mView.onSuccess(102, liveInfo);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }

    @Override
    public void getRoomInfo(Context context,String uid) {
        addSubscribe(mModel.getRecommend(context).subscribe(new RxSubscriber<RecommendInfo>() {
            @Override
            public void onSuccess(RecommendInfo recommendInfo) {
                mView.onSuccess(103, recommendInfo);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }
}
