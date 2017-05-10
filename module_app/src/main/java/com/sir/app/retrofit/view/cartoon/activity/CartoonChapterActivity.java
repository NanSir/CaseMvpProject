package com.sir.app.retrofit.view.cartoon.activity;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sir.app.cardstack.AllMoveDownAnimatorAdapter;
import com.sir.app.cardstack.CardStackView;
import com.sir.app.cardstack.UpDownAnimatorAdapter;
import com.sir.app.cardstack.UpDownStackAnimatorAdapter;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.base.BaseMvpActivity;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.Constant;
import com.sir.app.retrofit.contract.cartoon.CartoonContract;
import com.sir.app.retrofit.model.CollectDevice;
import com.sir.app.retrofit.model.cartoon.CartoonModelImpl;
import com.sir.app.retrofit.model.cartoon.bean.Chapter;
import com.sir.app.retrofit.model.cartoon.bean.ChapterReturn;
import com.sir.app.retrofit.presenter.cartoon.CartoonPresenterImpl;
import com.sir.app.retrofit.view.WebViewActivity;
import com.sir.app.retrofit.view.cartoon.adapter.ChapterAdapter;
import com.space.app.base.tools.ToolAlert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static cz.msebera.android.httpclient.HttpHeaders.IF;

/**
 * Created by zhuyinan on 2017/5/4.
 */

public class CartoonChapterActivity extends BaseMvpActivity<CartoonModelImpl, CartoonPresenterImpl> implements CartoonContract.View {

    @Bind(R.id.view_card_stack)
    CardStackView cardStackView;
    ChapterAdapter adapter;
    ChapterReturn chapter;
    CollectDevice device;
    String cartoonId;
    int page;

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_cartoon_chapter;
    }

    @Override
    public void initMvpView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String title = getIntent().getStringExtra("cartoonTitle");
        toolbarTitle.setText(title);
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter = new ChapterAdapter(getContext());
        device = new CollectDevice();//收藏
        cardStackView.setAdapter(adapter);
        cartoonId = getIntent().getStringExtra("cartoonId");
        loadingData(cartoonId, page = 1);

        adapter.setOnItemClickListener(new ChapterAdapter.ItemClickListener() {
            @Override
            public void OnItemClickListener(Chapter data) {
                StringBuffer sbUrl = new StringBuffer();
                sbUrl.append(NetWorkApi.SH_BaseUrl);
                sbUrl.append(NetWorkApi.SH_ReadComicBooksToIso);
                sbUrl.append("/" + data.getId());
                getOperation().addParameter("url", sbUrl.toString())
                        .addParameter("title", data.getTitle())
                        .forward(WebViewActivity.class);
            }
        });
    }

    private void loadingData(String id, int page) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("PageIndex", page + "");
        params.put("id", id);
        mPresenter.getChapterList(getContext(), params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chaper, menu);
        boolean isCollect = CollectDevice.isCollect(cartoonId, Constant.TYPE_CARTOON);
        menu.findItem(R.id.action_collect).setCheckable(isCollect);//收藏操作
        menu.findItem(R.id.action_collect).setIcon(isCollect ? R.mipmap.ic_collect_checked : R.mipmap.ic_collect_with);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stack_all_down:
                item.setChecked(true);
                cardStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(cardStackView));
                break;
            case R.id.stack_up_down:
                item.setChecked(true);
                cardStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(cardStackView));
                break;
            case R.id.stack_up_down_stack:
                item.setChecked(true);
                cardStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(cardStackView));
                break;
            case R.id.action_collect:
                item.setCheckable(!item.isCheckable());
                item.setIcon(item.isCheckable() ? R.mipmap.ic_collect_checked : R.mipmap.ic_collect_with);
                //收藏操作
                if (chapter != null) {
                    device.operateCollect(chapter.getParentItem(), item.isCheckable());
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFailure(String msg) {
        ToolAlert.showLong(getContext(), msg);
        if (adapter.getItemCount() == 0) {
            findViewById(R.id.coll_start).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccess(int code, Object object) {
        chapter = (ChapterReturn) object;
        adapter.setExplain(chapter.getParentItem().getExplain());
        setData(chapter.getList());
    }


    private void setData(List<Chapter> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            findViewById(R.id.coll_start).setVisibility(View.VISIBLE);
        }
    }
}
