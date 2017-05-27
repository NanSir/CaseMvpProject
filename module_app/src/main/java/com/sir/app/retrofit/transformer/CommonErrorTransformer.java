package com.sir.app.retrofit.transformer;

import com.sir.app.retrofit.net.exception.ExceptionHandle;

import rx.Observable;
import rx.functions.Func1;


public class CommonErrorTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> httpResponseObservable) {
        return httpResponseObservable.map(new Func1<T, T>() {
            @Override
            public T call(T response) {
                //服务器请求数据成功，返回里面的数据实体
                return response;
            }
            //  对请求服务器出现错误信息进行拦截
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                throwable.printStackTrace();
                return Observable.error(ExceptionHandle.handleException(throwable));
            }
        });
    }

    public static <T> CommonErrorTransformer<T> create() {
        return new CommonErrorTransformer<>();
    }

    private static CommonErrorTransformer instance = null;

    private CommonErrorTransformer() {
    }

    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T> CommonErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (CommonErrorTransformer.class) {
                if (instance == null) {
                    instance = new CommonErrorTransformer();
                }
            }
        }
        return instance;
    }
}
