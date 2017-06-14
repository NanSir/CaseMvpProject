package com.sir.app.retrofit.model.movie.bean;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class Story implements Serializable {

    private String showname;

    private StoryBrief data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public StoryBrief getData() {
        return data;
    }

    public void setData(StoryBrief data) {
        this.data = data;
    }

}
