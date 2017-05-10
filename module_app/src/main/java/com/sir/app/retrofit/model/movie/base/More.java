package com.sir.app.retrofit.model.movie.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuyinan on 2017/4/14.
 */

public class More implements Serializable {

    private String showname;

    private List<Label> data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public List<Label> getData() {
        return data;
    }

    public void setData(List<Label> data) {
        this.data = data;
    }

}
