package com.space.app.base;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by zhuyinan on 2016/12/9.
 * Contact by 445181052@qq.com
 */

public class BaseHttp {

    //封装的方法建议都加上Context参数，以在Activity pause或stop时取消掉没用的请求。

    private static final String BASE_URL = "http://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(context,url, params, responseHandler);
    }

    public static void post(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        //client.addHeader();
        //设置用户代理
        // client.setUserAgent();
        client.post(context,url, params, responseHandler);
    }

}
