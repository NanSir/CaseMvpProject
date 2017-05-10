package com.sir.app.retrofit.base;

/**
 * Created by zhuyinan on 2017/4/5.
 */

public interface BaseView {
    void onFailure(String msg);
    void onSuccess(int code, Object object);
}
