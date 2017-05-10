package com.sir.app.retrofit.model.video.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class VideoType implements Serializable {
    private String title;
    private String moreURL;
    private String pic;
    private String dataId;
    private String airTime;
    private String score;
    private String description;
    private String msg;
    private String phoneNumber;
    private String userPic;
    private String time;
    private String likeNum;

    private String showStyle;
    private String loadType;
    private String showType;


    @SerializedName("childList")
    private List<VideoInfo> childList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public List<VideoInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<VideoInfo> childList) {
        this.childList = childList;
    }

    public String getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(String showStyle) {
        this.showStyle = showStyle;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }
}
