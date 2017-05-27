package com.sir.app.retrofit.contract.live;

import android.content.Context;

import com.sir.app.retrofit.base.BaseModel;
import com.sir.app.retrofit.base.BasePresenter;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.sir.app.retrofit.model.live.bean.LiveInfo;
import com.sir.app.retrofit.model.live.bean.RecommendInfo;
import com.sir.app.retrofit.model.live.bean.RoomInfo;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;

/**
 *
 * Created by zhuyinan on 2017/5/26.
 */
public interface LiveContract {

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getAllCategories(Context context);
        public abstract void getRecommend(Context context);
        public abstract void getLiveInfo(Context context,String slug);
        public abstract void getRoomInfo(Context context,String uid);
    }

    interface Model extends BaseModel {
        Observable<List<LiveChannel>> getAllCategories(Context context);

        Observable<RecommendInfo> getRecommend(Context context);

        Observable<LiveInfo> getLiveInfo(Context context,@Path("slug") String slug);

        Observable<RoomInfo> getRoomInfo(Context context,@Path("uid") String uid);
    }

}
