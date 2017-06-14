package com.sir.app.retrofit.model.movie.bean;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class MovieData implements Serializable{

    private MovieReleaseType[] data;

    public MovieReleaseType[] getData() {
        return data;
    }

    public void setData(MovieReleaseType[] data) {
        this.data = data;
    }

}
