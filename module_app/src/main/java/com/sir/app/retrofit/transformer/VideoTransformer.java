package com.sir.app.retrofit.transformer;

import com.sir.app.retrofit.model.video.bean.VideoResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class VideoTransformer<T> implements Observable.Transformer<VideoResponse<T>, T> {
    @Override
    public Observable<T> call(Observable<VideoResponse<T>> httpResponseObservable) {
        return httpResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .compose(VideoErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
