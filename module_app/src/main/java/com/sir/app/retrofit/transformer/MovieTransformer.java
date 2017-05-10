package com.sir.app.retrofit.transformer;

import com.sir.app.retrofit.model.movie.base.MovieResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhuyinan on 2017/4/22.
 */

public class MovieTransformer<T> implements Observable.Transformer<MovieResponse<T>,T>{

    @Override
    public Observable<T> call(Observable<MovieResponse<T>> requestResultObservable) {
        return requestResultObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .compose(MovieErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
