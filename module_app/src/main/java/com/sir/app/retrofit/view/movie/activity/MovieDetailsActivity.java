package com.sir.app.retrofit.view.movie.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.movie.base.Label;
import com.sir.app.retrofit.model.movie.base.MovieModel;
import com.sir.app.retrofit.view.WebViewActivity;
import com.sir.app.retrofit.view.widget.WrapLayout;
import com.space.app.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 影片详情
 * Created by zhuyinan on 2017/4/13.
 */

public class MovieDetailsActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    boolean isCollapsed = false;
    MovieModel movieModel;

    @Bind(R.id.movie_poster)
    KenBurnsView moviePoster;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;

    @Bind(R.id.detail_rating_bar)
    SimpleRatingBar mRatingBar;
    @Bind(R.id.detail_rating_info_text)
    TextView mRatingInfoText;
    @Bind(R.id.detail_director_text)
    TextView mDirectorText;
    @Bind(R.id.detail_type_text)
    TextView mTypeText;
    @Bind(R.id.detail_starring_text)
    TextView mStarringText;
    @Bind(R.id.detail_story_brief_text)
    TextView mStoryBriefText;
    @Bind(R.id.detail_rating_text)
    TextView mRatingText;
    @Bind(R.id.detail_rating_layout)
    LinearLayout mRatingLayout;
    @Bind(R.id.detail_release_text)
    TextView mReleaseHintText;
    @Bind(R.id.detail_release_info_text)
    TextView mReleaseInfoText;

    @Bind(R.id.movie_frame)
    WrapLayout movieFrame;

    @Override
    public int bindLayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void doBusiness(Context mContext) {
        movieModel = (MovieModel) getIntent().getSerializableExtra("movie_model");
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        //通过CollapsingToolbarLayout修改字体颜色
        toolbarLayout.setTitle(movieModel.getName());
        toolbarLayout.setExpandedTitleColor(Color.YELLOW);//设置还没收缩时状态下字体颜色
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

        mAppBar.addOnOffsetChangedListener(this);

        initData(movieModel);
    }


    public void initData(MovieModel movieModel) {
        ImageLoader.getInstance().displayImage(movieModel.getPoster(), moviePoster);

        if (movieModel.getGrade() != null) {
            mRatingBar.setRating((Float.valueOf(movieModel.getGrade())) / 2.0f);
            mRatingBar.setFillColor(Color.RED);

            mRatingInfoText.setText(movieModel.getGrade());
            mRatingInfoText.append("" + movieModel.getGradeNum());
            mReleaseHintText.setText(getString(R.string.movie_release_info));
            mReleaseInfoText.setTextSize(16);
            mReleaseInfoText.setText(movieModel.getReleaseDateString());
            mReleaseInfoText.append("上映  " + movieModel.getCinemaNum());
        } else {
            mRatingText.setVisibility(View.GONE);
            mRatingLayout.setVisibility(View.GONE);
            mReleaseHintText.setText(getString(R.string.movie_release_date));
            mReleaseInfoText.setText(movieModel.getReleaseDateString());
            mReleaseInfoText.append(" 上映");
        }
        mDirectorText.setText(movieModel.getDirector().getData().getLabel1().getName());
        mTypeText.setText(movieModel.getMovieTypeString());
        mStarringText.setText(movieModel.getCastString());
        mStoryBriefText.setText(movieModel.getStory().getData().getStoryBrief());

        //更多数据
        List<Label> labels = movieModel.getMore().getData();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        for (final Label label : labels) {
            if (!TextUtils.isEmpty(label.getName()) && !label.getName().equals("选座购票")) {
                Button more = new Button(this);
                more.setLayoutParams(lp);
                more.setBackgroundResource(R.color.colorAccent);
                more.setTextColor(Color.WHITE);
                more.setTextSize(14);
                more.setTag(label.getLink());
                more.setText(label.getName());
                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getOperation().addParameter("url", label.getLink()).forward(WebViewActivity.class);
                    }
                });
                movieFrame.addView(more);
            }
        }


    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) { // 完全折叠状态
            if (!isCollapsed) {
                moviePoster.pause();
            }
            isCollapsed = true;
        } else { // 非折叠状态
            if (isCollapsed) {
                moviePoster.resume();
            }
            isCollapsed = false;
        }
    }

    @OnClick({R.id.movie_detail, R.id.movie_purchase})
    public void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.movie_detail://电影详情
                if (!movieModel.getWebUrl().isEmpty()) {
                    getOperation().addParameter("url", movieModel.getWebUrl()).forward(WebViewActivity.class);
                }
                break;
            case R.id.movie_purchase:
                if (movieModel.getMore().getData().size() > 0) {
                    List<Label> labels = movieModel.getMore().getData();
                    //获取最后一条数据（购票手机版网页）
                    String purchaseUrl = labels.get(labels.size() - 1).getLink();
                    if (!purchaseUrl.isEmpty()) {
                        getOperation().addParameter("url", purchaseUrl).forward(WebViewActivity.class);
                    }
                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isCollapsed) {
            moviePoster.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        moviePoster.pause();
    }

}
