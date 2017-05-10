package com.sir.app.retrofit.model.cartoon.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhuyinan on 2017/5/3.
 */

public class ChapterReturn {

    @SerializedName("List")
    private List<Chapter> chapters;

    private ParentItem ParentItem;

    private int ListCount;

    private int PageSize;

    private int PageIndex;

    public void setList(List<Chapter> List) {
        this.chapters = List;
    }

    public List<Chapter> getList() {
        return this.chapters;
    }

    public void setParentItem(ParentItem ParentItem) {
        this.ParentItem = ParentItem;
    }

    public ParentItem getParentItem() {
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
