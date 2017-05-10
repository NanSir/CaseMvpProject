package com.space.app.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment基类(V4包)
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {

    private View mContextView = null;
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;
    private Operation mBaseOperation = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null != mContextView) {
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (null != parent) {
                parent.removeView(mContextView);
            }
        } else {
            mContextView = inflater.inflate(bindLayout(), null);
            ButterKnife.bind(this, mContextView);
            initView(mContextView);
            //业务处理操作
            doBusiness(getActivity());
            isInitView = true;
            lazyLoad();
            mBaseOperation = new Operation(getActivity());
        }
        return mContextView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    private void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            //如果不是第一次加载，不是可见，不是初始化View，则不加载数据
            return;
        }
        lazyFetchData();
        isFirstLoad = false;//设置不是第一次加载
    }

    /**
     * 懒加载的方式获取数据
     * 仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    public void lazyFetchData() {

    }

    public View findViewById(@IdRes int id) {
        return mContextView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);//解绑
    }

    protected Operation getOperation() {
        return this.mBaseOperation;
    }

}
