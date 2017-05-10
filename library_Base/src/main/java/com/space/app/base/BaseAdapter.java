package com.space.app.base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adapter基类
 * Created by zhuyinan on 2016/4/26.
 * Contact by 445181052@qq.com
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    private List<T> mDataList;

    protected Activity mContext;

    private int mPerPageSize = 10;

    private BaseAdapter() {
        this(null);
    }

    public BaseAdapter(Activity mContext) {
        this(mContext, 10);
    }

    public BaseAdapter(Activity mContext, int mPerPageSize) {
        this.mContext = mContext;
        this.mPerPageSize = mPerPageSize;
        this.mDataList = new ArrayList<>();
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(bindLayout(), null);
            holder = ViewHolder.get(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        onBindHolder(holder, position);
        return view;
    }

    public abstract int bindLayout();

    public abstract void onBindHolder(ViewHolder holder, int position);

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        if (null != mDataList) {
            return mDataList.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (position < mDataList.size()) {
            return mDataList.get(position);
        } else {
            return null;
        }
    }


    public int getPageNo() {
        return (getCount() / mPerPageSize) + 1;
    }

    public boolean addItem(T object) {
        return mDataList.add(object);
    }

    public void addItem(int location, T object) {
        mDataList.add(location, object);
    }

    public boolean addItem(Collection<? extends T> collection) {
        return mDataList.addAll(collection);
    }

    public boolean addItem(int location, Collection<? extends T> collection) {
        return mDataList.addAll(location, collection);
    }

    public boolean removeItem(T object) {
        return mDataList.remove(object);
    }

    public T removeItem(int location) {
        return mDataList.remove(location);
    }

    public boolean removeAll(Collection<? extends T> collection) {
        return mDataList.removeAll(collection);
    }

    public void clear() {
        mDataList.clear();
    }

    public Activity getActivity() {
        if (null != mContext) {
            return mContext;
        }
        return null;
    }
}
