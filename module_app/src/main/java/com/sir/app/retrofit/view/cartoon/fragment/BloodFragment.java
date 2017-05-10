package com.sir.app.retrofit.view.cartoon.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.contract.cartoon.CartoonContract;
import com.sir.app.retrofit.model.cartoon.CartoonModelImpl;
import com.sir.app.retrofit.model.cartoon.bean.BookCartoon;
import com.sir.app.retrofit.model.cartoon.bean.BookReturn;
import com.sir.app.retrofit.presenter.cartoon.CartoonPresenterImpl;
import com.sir.app.retrofit.view.WebViewActivity;
import com.sir.app.retrofit.view.cartoon.activity.CartoonChapterActivity;
import com.sir.app.retrofit.view.cartoon.adapter.CartoonAdapter;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.space.app.base.data.ACache;
import com.space.app.base.tools.ToolAlert;
import com.space.app.base.tools.ToolSnackbar;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 热血模块
 * Created by zhuyinan on 2017/5/3.
 */

public class BloodFragment extends BaseMvpFragment<CartoonModelImpl, CartoonPresenterImpl> implements CartoonContract.View {

    @Bind(R.id.cartoon_ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @Bind(R.id.cartoon_recyclerView)
    RecyclerView mRecyclerView;
    CartoonAdapter mAdapter;

    protected int page = 0;

    @Override
    public int bindLayout() {
        return R.layout.fragment_cartoon_blood;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {

        mAdapter = new CartoonAdapter(getActivity());
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadingData(page = 1);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadingData(page = page + 1);
            }
        });

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ViewHolder>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                BookCartoon cartoon = mAdapter.getItem(position);
                getOperation().addParameter("cartoonId", cartoon.getId() + "")
                        .addParameter("cartoonTitle", cartoon.getTitle())
                        .forward(CartoonChapterActivity.class);
            }

            @Override
            public void onItemLongClick(ViewHolder viewHolder, int position) {

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        List<BookCartoon> list = (List<BookCartoon>) ACache.get(getContext()).getAsObject("BloodCartoon");
        if (list == null) {
            loadingData(page = 1);
        } else {
            setData(list);
        }
    }

    private void loadingData(int page) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("PageIndex", page + "");
        params.put("ClassifyId", "0");
        params.put("Size", "30");
        mPresenter.getAllBook(getContext(), params);
    }

    @Override
    public void onFailure(String msg) {
        ToolAlert.showLong(getContext(), msg);
        ptrLayout.refreshComplete();
    }

    @Override
    public void onSuccess(int code, Object object) {
        ptrLayout.refreshComplete();
        BookReturn bookReturn = (BookReturn) object;
        ACache.get(getContext()).put("BloodCartoon", (Serializable) bookReturn.getList());
        setData(bookReturn.getList());
    }

    private void setData(List<BookCartoon> list) {
        if (page == 1) {
            mAdapter.clear();
        }
        mAdapter.addItem(list);
    }

}
