package com.sir.app.retrofit.view.news.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.news.bean.NewsContent;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.willowtreeapps.spruce.animation.DefaultAnimations;

/**
 * Created by zhuyinan on 2017/4/7.
 */

public class NewsContentAdapter extends BaseRecyclerAdapter<NewsContent.Pagebean.Contentlist> {

    public NewsContentAdapter(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_news_adapter;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        NewsContent.Pagebean.Contentlist contentlist = getItem(position);
        if (contentlist.getImageurls() != null && contentlist.getImageurls().size() > 0) {
            ImageLoader.getInstance().displayImage(contentlist.getImageurls().get(0).getUrl(),
                    (ImageView) holder.getView(R.id.news_item_img), getImageOptions());
        }
        holder.setText(R.id.news_item_title, contentlist.getTitle());
        holder.setText(R.id.news_item_time, contentlist.getPubDate());
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[]{DefaultAnimations.shrinkAnimator(view, 800)};
    }
}
