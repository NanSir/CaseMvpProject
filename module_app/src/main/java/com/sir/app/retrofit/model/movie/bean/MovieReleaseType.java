package com.sir.app.retrofit.model.movie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class MovieReleaseType implements Serializable {

    private List<MovieModel> data;

    public List<MovieModel> getData() {
        return data;
    }

    public void setData(List<MovieModel> data) {
        this.data = data;
    }
}
