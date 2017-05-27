package com.sir.app.retrofit.view.video.live.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.live.bean.LiveChannel;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by zhuyinan on 2017/5/26.
 */

public class CategoriesAdapter extends BaseRecyclerAdapter<LiveChannel> {

    public CategoriesAdapter(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_live_categories;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        LiveChannel channel = getItem(position);
        holder.setText(R.id.categories_title, channel.getName());
        ImageLoader.getInstance().displayImage(channel.getIcon_image(), (ImageView) holder.getView(R.id.categories_icon));
    }
}
