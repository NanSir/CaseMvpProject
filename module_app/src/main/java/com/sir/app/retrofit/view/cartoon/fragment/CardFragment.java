package com.sir.app.retrofit.view.cartoon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.cartoon.bean.BookCartoon;
import com.space.app.base.BaseFragmentV4;

/**
 * 卡片
 * Created by zhuyinan on 2017/5/4.
 */

public class CardFragment extends BaseFragmentV4 {

    @Override
    public int bindLayout() {
        return R.layout.fragment_cartoon_card;
    }

    public static CardFragment getInstance(BookCartoon card) {
        CardFragment localCardFragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", card);
        localCardFragment.setArguments(bundle);
        return localCardFragment;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        BookCartoon cartoon = (BookCartoon) getArguments().getSerializable("book");
        setData(R.id.cartoon_title, cartoon.getTitle());
        setData(R.id.cartoon_subtitle, cartoon.getLastChapter().getTitle());
        setData(R.id.cartoon_author, cartoon.getAuthor());
        setData(R.id.cartoon_digest, cartoon.getExplain());
        setData(R.id.cartoon_refresh_time, cartoon.getRefreshTimeStr());
        ImageView im = (ImageView) findViewById(R.id.cartoon_cover);
        ImageLoader.getInstance().displayImage(cartoon.getFrontCover(), im);
    }


    public void setData(@IdRes int id, String value) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(value);
    }
}
