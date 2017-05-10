package com.sir.app.retrofit.view.movie;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.common.ViewPagerAdapter;
import com.sir.app.retrofit.contract.movie.MovieContract;
import com.sir.app.retrofit.model.movie.MovieModelImpl;
import com.sir.app.retrofit.model.movie.base.MovieData;
import com.sir.app.retrofit.presenter.movie.MoviePresenterImpl;
import com.sir.app.retrofit.view.movie.fragment.MovieListFragment;
import com.space.app.base.data.ACache;
import com.space.app.base.tools.ToolSnackbar;

import butterknife.Bind;

/**
 * 电影资讯板块主界面
 * Created by zhuyinan on 2017/4/1.
 */

public class MovieMainFragment extends BaseMvpFragment<MovieModelImpl, MoviePresenterImpl> implements MovieContract.View {

    @Bind(R.id.movie_tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.movie_viewPager)
    ViewPager viewPager;

    @Override
    public int bindLayout() {
        return R.layout.fragment_movie_main;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {
        tabLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void doBusiness(Context mContext) {
        MovieData movieModel = (MovieData) ACache.get(getContext()).getAsObject("MovieData");
        if (movieModel == null) {
            mPresenter.getRecentShadow(getContext(), "深圳");
        } else {
            setData(movieModel);
        }
    }

    @Override
    public void onFailure(String msg) {
        ToolSnackbar.showShort(findViewById(R.id.view_coordinator), msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        if (code == 100) {
            MovieData movieData = (MovieData) object;
            setData(movieData);
            ACache.get(getContext()).put("MovieData", movieData);
        }
    }

    public void setData(MovieData movieData) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(MovieListFragment.newInstance(movieData.getData()[0].getData()), "正在热映");
        adapter.addFrag(MovieListFragment.newInstance(movieData.getData()[1].getData()), "即将上映");
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
