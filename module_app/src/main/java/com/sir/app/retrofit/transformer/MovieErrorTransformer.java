package com.sir.app.retrofit.transformer;

import android.util.Log;

import com.sir.app.retrofit.model.movie.bean.MovieResponse;
import com.sir.app.retrofit.net.exception.ExceptionHandle;
import com.sir.app.retrofit.net.exception.ServerException;

import rx.Observable;
import rx.functions.Func1;


public class MovieErrorTransformer<T> implements Observable.Transformer<MovieResponse<T>, T> {
    @Override
    public Observable<T> call(Observable<MovieResponse<T>> httpResponseObservable) {
        return httpResponseObservable.map(new Func1<MovieResponse<T>, T>() {
            @Override
            public T call(MovieResponse<T> tHttpResponse) {
                if (tHttpResponse.getError_code() != 0) {
                    Log.e("ErrorTransformer", tHttpResponse.toString());
                    //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                    throw new ServerException(String.valueOf(tHttpResponse.getReason()), tHttpResponse.getError_code());
                }
                //服务器请求数据成功，返回里面的数据实体
                return tHttpResponse.getResult();
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

    public static <T> MovieErrorTransformer<T> create() {
        return new MovieErrorTransformer<>();
    }

    private static MovieErrorTransformer instance = null;

    private MovieErrorTransformer() {
    }

    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T> MovieErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (MovieErrorTransformer.class) {
                if (instance == null) {
                    instance = new MovieErrorTransformer();
                }
            }
        }
        return instance;
    }
}
