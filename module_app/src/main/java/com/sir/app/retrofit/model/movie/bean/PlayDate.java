package com.sir.app.retrofit.model.movie.bean;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class PlayDate  implements Serializable{

    private String showname;

    private String data;

    private String data2;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

}
