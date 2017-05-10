package com.sir.app.retrofit.contract.cartoon;

import android.content.Context;

import com.sir.app.retrofit.base.BaseModel;
import com.sir.app.retrofit.base.BasePresenter;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.model.cartoon.bean.BookReturn;
import com.sir.app.retrofit.model.cartoon.bean.ChapterReturn;

import java.util.Map;

import rx.Observable;

/**
 * Created by zhuyinan on 2017/5/2.
 */

public interface CartoonContract {

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getAllBook(Context context, Map<String,String> params);
        public abstract void getChapterList(Context context, Map<String,String> params);
        public abstract void getBookDetails(Context context, String id);
    }

    interface Model extends BaseModel {
        Observable<BookReturn> getAllBook(Context context, Map<String,String> params);
        Observable<ChapterReturn> getChapterList(Context context, Map<String,String> params);
        Observable<ChapterReturn> getBookDetails(Context context, String id);

    }

}