package com.sir.app.retrofit.model.cartoon.bean;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/5/3.
 */

public class BookLastChapter implements Serializable{

    private String AnotherId;

    private int Id;

    private String Title;

    private int Sort;

    private String FrontCover;

    private String Images;

    private String RefreshTime;

    private String RefreshTimeStr;

    private String Book;

    private String PostUser;

    private int ChapterNo;

    private int Reel;

    private int BookId;

    private int ChapterType;

    private String DownLoadUrl;

    private String Copyright;

    private String Tencent;

    private String ExceptionChapter;

    private String CreateTime;

    public void setAnotherId(String AnotherId) {
        this.AnotherId = AnotherId;
    }

    public String getAnotherId() {
        return this.AnotherId;
    }

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

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    public int getSort() {
        return this.Sort;
    }

    public void setFrontCover(String FrontCover) {
        this.FrontCover = FrontCover;
    }

    public String getFrontCover() {
        return this.FrontCover;
    }

    public void setImages(String Images) {
        this.Images = Images;
    }

    public String getImages() {
        return this.Images;
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

    public void setBook(String Book) {
        this.Book = Book;
    }

    public String getBook() {
        return this.Book;
    }

    public void setPostUser(String PostUser) {
        this.PostUser = PostUser;
    }

    public String getPostUser() {
        return this.PostUser;
    }

    public void setChapterNo(int ChapterNo) {
        this.ChapterNo = ChapterNo;
    }

    public int getChapterNo() {
        return this.ChapterNo;
    }

    public void setReel(int Reel) {
        this.Reel = Reel;
    }

    public int getReel() {
        return this.Reel;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
    }

    public int getBookId() {
        return this.BookId;
    }

    public void setChapterType(int ChapterType) {
        this.ChapterType = ChapterType;
    }

    public int getChapterType() {
        return this.ChapterType;
    }

    public void setDownLoadUrl(String DownLoadUrl) {
        this.DownLoadUrl = DownLoadUrl;
    }

    public String getDownLoadUrl() {
        return this.DownLoadUrl;
    }

    public void setCopyright(String Copyright) {
        this.Copyright = Copyright;
    }

    public String getCopyright() {
        return this.Copyright;
    }

    public void setTencent(String Tencent) {
        this.Tencent = Tencent;
    }

    public String getTencent() {
        return this.Tencent;
    }

    public void setExceptionChapter(String ExceptionChapter) {
        this.ExceptionChapter = ExceptionChapter;
    }

    public String getExceptionChapter() {
        return this.ExceptionChapter;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

}
