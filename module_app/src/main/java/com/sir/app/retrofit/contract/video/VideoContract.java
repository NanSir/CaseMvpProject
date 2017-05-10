package com.sir.app.retrofit.contract.video;

import android.content.Context;

import com.sir.app.retrofit.base.BaseModel;
import com.sir.app.retrofit.base.BasePresenter;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.model.video.bean.VideoRes;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuyinan on 2017/4/14.
 */

public interface VideoContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHomeInfo(Context context);

        public abstract void getVideoList(Context context, String catalogId, int page);

        public abstract void getVideoInfo(Context context, String mediaId);
    }

    interface Model extends BaseModel {
        Observable<VideoRes> getHomeInfo(Context context);

        Observable<VideoRes> getVideoList(Context context, String catalogId, int page);

        Observable<VideoRes> getVideoInfo(Context context, String mediaId);
    }


}