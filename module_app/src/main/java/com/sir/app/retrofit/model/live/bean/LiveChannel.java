package com.sir.app.retrofit.model.live.bean;

import java.io.Serializable;

/**
 * 直播频道
 * Created by zhuyinan on 2017/5/26.
 */
public class LiveChannel implements Serializable{

    private int id;

    private String name;

    private int is_default;

    private int sort;

    private String icon_gray;

    private String icon_red;

    private String icon_image;

    private String slug;

    private int type;

    private int screen;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public int getIs_default() {
        return this.is_default;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return this.sort;
    }

    public void setIcon_gray(String icon_gray) {
        this.icon_gray = icon_gray;
    }

    public String getIcon_gray() {
        return this.icon_gray;
    }

    public void setIcon_red(String icon_red) {
        this.icon_red = icon_red;
    }

    public String getIcon_red() {
        return this.icon_red;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getIcon_image() {
        return this.icon_image;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public int getScreen() {
        return this.screen;
    }

}
