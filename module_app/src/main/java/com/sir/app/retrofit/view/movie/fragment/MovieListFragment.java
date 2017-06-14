package com.sir.app.retrofit.view.movie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.base.BaseMvpFragment;
import com.sir.app.retrofit.base.BaseView;
import com.sir.app.retrofit.contract.movie.MovieContract;
import com.sir.app.retrofit.model.movie.MovieModelImpl;
import com.sir.app.retrofit.model.movie.bean.MovieModel;
import com.sir.app.retrofit.presenter.movie.MoviePresenterImpl;
import com.sir.app.retrofit.view.movie.activity.MovieDetailsActivity;
import com.sir.app.retrofit.view.movie.adapter.MovieListAdapter;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;
import com.space.app.base.tools.ToolAlert;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;

/**
 * 影片列表
 * Created by zhuyinan on 2017/4/13.
 */

public class MovieListFragment extends BaseMvpFragment<MovieModelImpl, MoviePresenterImpl> implements MovieContract.View {

    @Bind(R.id.movie_recyclerView)
    RecyclerView movieRecyclerView;
    MovieListAdapter movieAdapter;
    int mReleaseType;

    @Override
    public int bindLayout() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    public void initMvpView(View view) {
        mReleaseType = getArguments().getInt("type");
    }

    @Override
    public void doBusiness(Context mContext) {
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieListAdapter(getActivity());
        movieAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ViewHolder>() {
            @Override
            public void onItemClick(ViewHolder holder, int position) {
                MovieModel movieModel = movieAdapter.getItem(position);
                getOperation().addParameter("movie_model", movieModel).forward(MovieDetailsActivity.class);
            }

            @Override
            public void onItemLongClick(ViewHolder holder, int position) {

            }
        });
        movieRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void lazyFetchData() {
        List<MovieModel> list = (List<MovieModel>) getArguments().getSerializable("MovieData");
        movieAdapter.addItem(list);
    }

    @Override
    public void onFailure(String msg) {
        ToolAlert.showShort(getContext(), "服务器异常：" + msg);
    }

    @Override
    public void onSuccess(int code, Object object) {
        if (code == 100) {
            List<MovieModel> list = (List<MovieModel>) object;
            movieAdapter.addItem(list);
        }
    }

    public static MovieListFragment newInstance(List<MovieModel> data) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("MovieData", (Serializable) data);
        fragment.setArguments(bundle);
        return fragment;
    }
}
