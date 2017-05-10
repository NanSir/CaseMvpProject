package com.sir.app.retrofit.view.movie.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.movie.base.MovieModel;
import com.sir.app.retrofit.view.widget.TagGroup;
import com.space.app.base.BaseRecyclerAdapter;
import com.space.app.base.ViewHolder;

/**
 * 电影列表适配器
 * Created by zhuyinan on 2017/4/12.
 */

public class MovieListAdapter extends BaseRecyclerAdapter<MovieModel> {

    public MovieListAdapter(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_movie_adapter;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        final MovieModel movieModel = getItem(position);

        holder.setText(R.id.movie_name_text, movieModel.getName());
        holder.setText(R.id.movie_cast_text, movieModel.getCastString());

        TagGroup tagContainer = holder.getView(R.id.movie_type_container);
        tagContainer.setTagData(movieModel.getMovieTypeList(), R.color.colorPrimary);

        SimpleRatingBar ratingBar = holder.getView(R.id.movie_rating_bar);
        if (movieModel.getGrade() != null) {
            holder.setText(R.id.movie_grade_text, movieModel.getGrade());
            ratingBar.setRating((Float.valueOf(movieModel.getGrade())) / 2.0f);
            ratingBar.setFillColor(Color.RED);
            holder.getView(R.id.movie_release_date_text).setVisibility(View.INVISIBLE);
        } else {
            ratingBar.setVisibility(View.INVISIBLE);
            holder.getView(R.id.movie_grade_text).setVisibility(View.INVISIBLE);
            holder.setText(R.id.movie_release_date_text, movieModel.getReleaseDateString() + " 上映");
        }
        ImageView posterImg = holder.getView(R.id.movie_poster_img);
        ImageLoader.getInstance().displayImage(movieModel.getPoster(), posterImg, getImageOptions());
    }
}
