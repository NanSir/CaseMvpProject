package com.sir.app.retrofit.transformer;

import android.util.Log;

import com.sir.app.retrofit.model.video.bean.VideoResponse;
import com.sir.app.retrofit.net.exception.ExceptionHandle;
import com.sir.app.retrofit.net.exception.ServerException;

import rx.Observable;
import rx.functions.Func1;


public class VideoErrorTransformer<T> implements Observable.Transformer<VideoResponse<T>, T> {
    @Override
    public Observable<T> call(Observable<VideoResponse<T>> httpResponseObservable) {
        //   对服务器端给出Json数据进行校验
        return httpResponseObservable.map(new Func1<VideoResponse<T>, T>() {
            @Override
            public T call(VideoResponse<T> tHttpResponse) {
                if (tHttpResponse.getCode() != 200) {
                    //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                    throw new ServerException(String.valueOf(tHttpResponse.getMsg()), tHttpResponse.getCode());
                }
                //服务器请求数据成功，返回里面的数据实体
                return tHttpResponse.getRet();
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

    public static <T> VideoErrorTransformer<T> create() {
        return new VideoErrorTransformer<>();
    }

    private static VideoErrorTransformer instance = null;

    private VideoErrorTransformer() {
    }

    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T> VideoErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (VideoErrorTransformer.class) {
                if (instance == null) {
                    instance = new VideoErrorTransformer();
                }
            }
        }
        return instance;
    }
}
