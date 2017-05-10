package com.sir.app.retrofit.view.cartoon.fragment;

import android.content.Context;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.contract.cartoon.CartoonContract;
import com.sir.app.retrofit.model.cartoon.CartoonModelImpl;
import com.sir.app.retrofit.presenter.cartoon.CartoonPresenterImpl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 国产
 * Created by zhuyinan on 2017/5/3.
 */

public class DomesticFragment extends BaseMvpFragment<CartoonModelImpl, CartoonPresenterImpl> implements CartoonContract.View {
    @Override
    public int bindLayout() {
        return R.layout.fragment_cartoon_domestic;
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {

    }

    @Override
    public void onFailure(String msg) {

    }

    private void loadingData(int page) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("PageIndex", page + "");
        params.put("ClassifyId", "2");
        params.put("Size", "30");
        mPresenter.getAllBook(getContext(), params);
    }


    @Override
    public void onSuccess(int code, Object object) {

    }

}
