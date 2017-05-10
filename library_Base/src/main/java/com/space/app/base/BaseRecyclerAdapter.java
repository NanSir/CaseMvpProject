package com.space.app.base;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.R.attr.data;
import static android.R.string.no;

/**
 * RecyclerAdapter基类
 * Created by zhuyinan on 2016/12/1.
 * Contact by 445181052@qq.com
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected Activity mContext;
    protected List<T> mDataList;

    private boolean isShowAnim = true; // 是否播放item进入动画
    private int mLastPosition = -1;

    protected OnItemClickListener<ViewHolder> listener;

    private BaseRecyclerAdapter() {

    }

    public BaseRecyclerAdapter(Activity context) {
        this.mContext = context;
        this.mDataList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        onBindHolder(holder, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(holder, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null) {
                    listener.onItemLongClick(holder, position);
                    return true;
                }
                return false;
            }
        });

        //item进入动画
        Animator[] animators = getAnimators(holder.itemView);
        if (isShowAnim && animators != null && animators.length > 0
                && holder.getAdapterPosition() > mLastPosition) {
            if (animators.length > 1) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animators);
                animatorSet.start();
            } else {
                for (Animator animator : animators) {
                    animator.start();
                }
            }
            mLastPosition = holder.getAdapterPosition();
        }
    }

    protected Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f).setDuration(500),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 100, 0).setDuration(500)};
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(mContext, bindLayout(), parent);
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void addItem(Collection<? extends T> list) {
        if (list != null) {
            mDataList.addAll(list);
            notifyItemChanged(getItemCount()-1);
        }
    }

    public T getItem(ViewHolder holder) {
        return mDataList.get(holder.getLayoutPosition());
    }

    public T getItem(int position) {
        return mDataList.get(position);
    }

    public void addItem(T data) {
        mDataList.add(data);
        notifyItemInserted(getItemCount());
    }

    /**
     * 使用
     * holder.getAdapterPosition()
     * 获取location
     * @param location
     */
    public void removeItem(int location) {
        mDataList.remove(location);
        notifyItemRemoved(location);

    }


    public void clear() {
        mDataList.clear();
    }

    public List<T> getAll() {
        return mDataList;
    }

    private DisplayImageOptions mOption;

    public DisplayImageOptions getImageOptions() {
        if (mOption == null) {
            mOption = ((BaseApplication) BaseApplication.getContext()).getImageOptions();
        }
        return mOption;
    }

    public boolean isShowAnim() {
        return isShowAnim;
    }


    public DisplayImageOptions getImageOptions(int onLoading, int onFail, int emptyUri) {
        if (mOption == null) {
            mOption = ((BaseApplication) BaseApplication.getContext()).getImageOptions(onLoading, onFail, emptyUri);
        }
        return mOption;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int position);

        void onItemLongClick(T t, int position);
    }

    public abstract int bindLayout();

    public abstract void onBindHolder(ViewHolder holder, int position);

}
