package com.sir.app.retrofit.model.cartoon.bean;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/5/3.
 */

public class BookCartoon implements Serializable{

    private int Id;

    private String Title;

    private String FrontCover;

    private String RefreshTime;

    private String RefreshTimeStr;

    private String Explain;

    private String SerializedState;

    private String Author;

    private int LastChapterNo;

    private int ClassifyId;

    private BookLastChapter LastChapter;

    private String Chapters;

    private String MoreUrl;

    private boolean Recommend;

    private int Copyright;

    private int Sort;

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getId() {
        return this.Id;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setFrontCover(String FrontCover) {
        this.FrontCover = FrontCover;
    }

    public String getFrontCover() {
        return this.FrontCover;
    }

    public void setRefreshTime(String RefreshTime) {
        this.RefreshTime = RefreshTime;
    }

    public String getRefreshTime() {
        return this.RefreshTime;
    }

    public void setRefreshTimeStr(String RefreshTimeStr) {
        this.RefreshTimeStr = RefreshTimeStr;
    }

    public String getRefreshTimeStr() {
        return this.RefreshTimeStr;
    }

    public void setExplain(String Explain) {
        this.Explain = Explain;
    }

    public String getExplain() {
        return this.Explain;
    }

    public void setSerializedState(String SerializedState) {
        this.SerializedState = SerializedState;
    }

    public String getSerializedState() {
        return this.SerializedState;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getAuthor() {
        return this.Author;
    }

    public void setLastChapterNo(int LastChapterNo) {
        this.LastChapterNo = LastChapterNo;
    }

    public int getLastChapterNo() {
        return this.LastChapterNo;
    }

    public void setClassifyId(int ClassifyId) {
        this.ClassifyId = ClassifyId;
    }

    public int getClassifyId() {
        return this.ClassifyId;
    }

    public void setLastChapter(BookLastChapter LastChapter) {
        this.LastChapter = LastChapter;
    }

    public BookLastChapter getLastChapter() {
        return this.LastChapter;
    }

    public void setChapters(String Chapters) {
        this.Chapters = Chapters;
    }

    public String getChapters() {
        return this.Chapters;
    }

    public void setMoreUrl(String MoreUrl) {
        this.MoreUrl = MoreUrl;
    }

    public String getMoreUrl() {
        return this.MoreUrl;
    }

    public void setRecommend(boolean Recommend) {
        this.Recommend = Recommend;
    }

    public boolean getRecommend() {
        return this.Recommend;
    }

    public void setCopyright(int Copyright) {
        this.Copyright = Copyright;
    }

    public int getCopyright() {
        return this.Copyright;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    public int getSort() {
        return this.Sort;
    }

}
