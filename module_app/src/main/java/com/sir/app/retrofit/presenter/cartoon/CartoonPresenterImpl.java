package com.sir.app.retrofit.presenter.cartoon;


import android.content.Context;

import com.sir.app.retrofit.contract.cartoon.CartoonContract;
import com.sir.app.retrofit.model.cartoon.bean.BookReturn;
import com.sir.app.retrofit.model.cartoon.bean.ChapterReturn;
import com.sir.app.retrofit.net.callback.RxSubscriber;
import com.sir.app.retrofit.net.exception.ResponseThrowable;

import java.util.Map;

/**
 * Created by zhuyinan on 2017/05/02
 */

public class CartoonPresenterImpl extends CartoonContract.Presenter {

    @Override
    public void getAllBook(Context context, Map<String, String> params) {
            addSubscribe(mModel.getAllBook(context,params).subscribe(new RxSubscriber<BookReturn>() {
                @Override
                public void onSuccess(BookReturn bookReturn) {
                    mView.onSuccess(100,bookReturn);
                }

                @Override
                protected void onError(ResponseThrowable ex) {
                    mView.onFailure(ex.message);
                }
            }));
    }

    @Override
    public void getChapterList(Context context, Map<String, String> params) {
            addSubscribe(mModel.getChapterList(context,params).subscribe(new RxSubscriber<ChapterReturn>() {
                @Override
                public void onSuccess(ChapterReturn chapter) {
                    mView.onSuccess(101,chapter);
                }

                @Override
                protected void onError(ResponseThrowable ex) {
                    mView.onFailure(ex.message);
                }
            }));
    }

    @Override
    public void getBookDetails(Context context, String id) {

    }
}