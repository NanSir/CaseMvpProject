package com.sir.app.retrofit.presenter.movie;

import android.content.Context;

import com.sir.app.retrofit.contract.movie.MovieContract;
import com.sir.app.retrofit.model.movie.bean.MovieData;
import com.sir.app.retrofit.net.callback.RxSubscriber;
import com.sir.app.retrofit.net.exception.ResponseThrowable;

/**
 * Created by zhuyinan on 2017/04/11
 */

public class MoviePresenterImpl extends MovieContract.Presenter {

    @Override
    public void getRecentShadow(Context context, String city) {
        addSubscribe(mModel.getRecentShadow(context, city).subscribe(new RxSubscriber<MovieData>() {
            @Override
            public void onSuccess(MovieData movieData) {
                mView.onSuccess(100, movieData);
            }

            @Override
            protected void onError(ResponseThrowable ex) {
                mView.onFailure(ex.message);
            }
        }));
    }
}