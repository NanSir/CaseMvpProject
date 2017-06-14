package com.sir.app.retrofit.contract.movie;

import android.content.Context;

import com.sir.app.retrofit.base.BaseModel;
import com.sir.app.retrofit.base.BasePresenter;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.model.movie.bean.MovieData;

import rx.Observable;

/**
 * Created by zhuyinan on 2017/4/11.
 */

public interface MovieContract {

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getRecentShadow(Context context, String city);
    }

    interface Model extends BaseModel {
        Observable<MovieData> getRecentShadow(Context context, String city);
    }


}