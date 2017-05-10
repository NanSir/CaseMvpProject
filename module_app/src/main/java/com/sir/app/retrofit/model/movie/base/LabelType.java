package com.sir.app.retrofit.model.movie.base;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class LabelType implements Serializable {

    private String showname;

    private LabelGroup data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public LabelGroup getData() {
        return data;
    }

    public void setData(LabelGroup data) {
        this.data = data;
    }

}
