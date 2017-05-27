package com.sir.app.retrofit.model.live.bean;

import java.util.List;

/**
 * 直播列表
 * Created by zhuyinan on 2017/5/26.
 */
public class LiveInfo {

    private int total;

    private List<LiveDetails> data;

    private String icon;

    private int pageCount;

    private int page;

    private int size;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setData(List<LiveDetails> data) {
        this.data = data;
    }

    public List<LiveDetails> getData() {
        return this.data;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

}
