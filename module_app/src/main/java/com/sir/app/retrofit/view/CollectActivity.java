package com.sir.app.retrofit.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.common.Constant;
import com.sir.app.retrofit.model.CollectDevice;
import com.sir.app.retrofit.view.cartoon.activity.CartoonChapterActivity;
import com.sir.app.retrofit.view.video.activity.VideoPlayActivity;
import com.space.app.base.BaseActivity;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.space.app.base.utils.LiteOrmDBUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 收藏列表
 * Created by zhuyinan on 2017/4/19.
 */

public class CollectActivity extends BaseActivity {

    @Bind(R.id.coll_recyclerView)
    RecyclerView collRecyclerView;
    CollectAdapter adapter;


    @Override
    public int bindLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public void initView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(getTitle());
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter = new CollectAdapter(getContext());
        collRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        collRecyclerView.setAdapter(adapter);
        final List<CollectDevice.Collect> list = CollectDevice.selectCollects(Constant.REG_COLLECT);
        adapter.addItem(list);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                CollectDevice.Collect collect = list.get(position);
                if (collect.getType().equals(Constant.TYPE_VIDEO)) {
                    getOperation().addParameter("mediaId", collect.getDataId())
                            .addParameter("mediaPic", collect.getPic())
                            .forward(VideoPlayActivity.class);
                } else if (collect.getType().equals(Constant.TYPE_CARTOON)) {
                    getOperation().addParameter("cartoonTitle", collect.getTitle())
                            .addParameter("cartoonId", collect.getDataId() + "")
                            .forward(CartoonChapterActivity.class);
                } else if (collect.getType().equals(Constant.TYPE_NEWS)) {
                    getOperation().addParameter("url", collect.getAddress())
                            .forward(WebViewActivity.class);
                }
            }

            @Override
            public void onItemLongClick(Object o, int position) {

            }
        });

        findViewById(R.id.progress).setVisibility(View.GONE);
        if (adapter.getItemCount() == 0) {
            findViewById(R.id.layout_no_more).setVisibility(View.VISIBLE);
        }
    }


    public class CollectAdapter extends BaseRecyclerAdapter<CollectDevice.Collect> {

        public CollectAdapter(Activity context) {
            super(context);
        }

        @Override
        public int bindLayout() {
            return R.layout.item_collect;
        }

        @Override
        public void onBindHolder(final ViewHolder holder, final int position) {
            final CollectDevice.Collect collect = getItem(position);
            holder.setText(R.id.coll_title, collect.getTitle());
            holder.setText(R.id.coll_date, collect.getDate());
            holder.setText(R.id.coll_content, collect.getDescription());
            ImageLoader.getInstance().displayImage(collect.getPic(), (ImageView) holder.getView(R.id.coll_pic), getImageOptions());
            holder.getView(R.id.coll_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(holder.getAdapterPosition());
                    LiteOrmDBUtils.deleteWhere(CollectDevice.Collect.class, "id", new String[]{collect.getId() + ""});
                }
            });

            holder.getView(R.id.coll_pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(holder, position);
                    }
                }
            });
        }
    }
}
