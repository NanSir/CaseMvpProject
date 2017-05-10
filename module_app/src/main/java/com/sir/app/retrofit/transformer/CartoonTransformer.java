package com.sir.app.retrofit.transformer;

import com.sir.app.retrofit.model.cartoon.bean.BookResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhuyinan on 2017/4/22.
 */

public class CartoonTransformer<T> implements Observable.Transformer<BookResponse<T>,T>{

    @Override
    public Observable<T> call(Observable<BookResponse<T>> requestResultObservable) {
        return requestResultObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .compose(CartoonErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
