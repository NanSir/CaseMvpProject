package com.sir.app.retrofit.view.cartoon.fragment;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.ViewPagerAdapter;
import com.sir.app.retrofit.contract.cartoon.CartoonContract;
import com.sir.app.retrofit.model.cartoon.CartoonModelImpl;
import com.sir.app.retrofit.model.cartoon.bean.BookCartoon;
import com.sir.app.retrofit.model.cartoon.bean.BookReturn;
import com.sir.app.retrofit.presenter.cartoon.CartoonPresenterImpl;
import com.sir.app.retrofit.view.cartoon.activity.CartoonChapterActivity;
import com.space.app.base.data.ACache;
import com.space.app.base.tools.ToolAlert;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 精选
 * Created by zhuyinan on 2017/5/3.
 */

public class ChoiceFragment extends BaseMvpFragment<CartoonModelImpl, CartoonPresenterImpl> implements CartoonContract.View {

    @Bind(R.id.cartoon_choice)
    ViewPager mViewPager;
    ViewPagerAdapter mAdapter;
    List<BookCartoon> dataList;
    int page;

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_cartoon_choiceness;
    }

    @Override
    public void initMvpView(View view) {
        mAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageMargin(30);
    }

    @Override
    public void doBusiness(Context mContext) {
        List<BookCartoon> list = (List<BookCartoon>) ACache.get(getContext()).getAsObject("ChoiceCartoon");
        if (list == null) {
            loadingData(page = 1);
        } else {
            setData(list);
        }
    }


    private void loadingData(int page) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("PageIndex", page + "");
        params.put("ClassifyId", "2");
        params.put("Size", "30");
        mPresenter.getAllBook(getContext(), params);
    }


    @OnClick(R.id.book_read)
    public void onClickRead(View view) {
        BookCartoon cartoon = dataList.get(mViewPager.getCurrentItem());
        getOperation().addParameter("cartoonTitle", cartoon.getTitle())
                .addParameter("cartoonId", cartoon.getId() + "")
                .forward(CartoonChapterActivity.class);
    }

    @Override
    public void onFailure(String msg) {
        ToolAlert.showLong(getContext(), msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        BookReturn bookReturn = (BookReturn) object;
        ACache.get(getContext()).put("ChoiceCartoon", (Serializable) bookReturn.getList());
        setData(bookReturn.getList());
    }

    private void setData(List<BookCartoon> list) {
        for (BookCartoon cartoon : list) {
            mAdapter.addFrag(CardFragment.getInstance(cartoon), cartoon.getTitle());
        }
        dataList = list;
        mAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0);
    }
}
