package com.sir.app.retrofit.model.movie.base;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class Label implements Serializable {

    private String name;

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
