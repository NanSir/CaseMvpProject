package com.sir.app.retrofit.model.cartoon.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuyinan on 2017/5/3.
 */

public class BookReturn implements Serializable{

    @SerializedName("List")
    private List<BookCartoon> bookCartoons;

    private String ParentItem;

    private int ListCount;

    private int PageSize;

    private int PageIndex;

    public void setList(List<BookCartoon> list) {
        this.bookCartoons = list;
    }

    public List<BookCartoon> getList() {
        return this.bookCartoons;
    }

    public void setParentItem(String ParentItem) {
        this.ParentItem = ParentItem;
    }

    public String getParentItem() {
        return this.ParentItem;
    }

    public void setListCount(int ListCount) {
        this.ListCount = ListCount;
    }

    public int getListCount() {
        return this.ListCount;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public int getPageSize() {
        return this.PageSize;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageIndex() {
        return this.PageIndex;
    }

}
