package com.sir.app.retrofit.view.cartoon.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.cartoon.bean.BookCartoon;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;

/**
 * Created by zhuyinan on 2017/5/4.
 */

public class CartoonAdapter extends BaseRecyclerAdapter<BookCartoon> {


    int width;

    public CartoonAdapter(Activity mContext) {
        super(mContext);
        width = mContext.getWindowManager().getDefaultDisplay().getWidth();
    }


    @Override
    public int bindLayout() {
        return R.layout.item_cartoon;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        BookCartoon cartoon = getItem(position);
        holder.setText(R.id.cartoon_title, cartoon.getTitle());
        holder.setText(R.id.cartoon_digest, cartoon.getExplain());
        holder.setText(R.id.cartoon_author, cartoon.getAuthor());
        holder.setText(R.id.cartoon_refresh_time, cartoon.getRefreshTimeStr());
        ImageView im = holder.getView(R.id.cartoon_cover);

        ViewGroup.LayoutParams params = im.getLayoutParams();
        params.width = width / 2;
        params.height = (int) (200 + Math.random() * 400);
        im.setLayoutParams(params);

        ImageLoader.getInstance().displayImage(cartoon.getFrontCover(), im, getImageOptions());
    }
}
