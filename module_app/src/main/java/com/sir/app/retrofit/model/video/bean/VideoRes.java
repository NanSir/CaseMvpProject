package com.sir.app.retrofit.model.video.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class VideoRes implements Serializable {

    @SerializedName("list")
    private List<VideoType> list;
    private List<VideoInfo> bannerList;
    private String title;
    private String score;
    private String videoType;
    private String region;
    private String airTime;
    private String director;
    private String actors;
    private String pic;
    private String description;
    private String smoothURL;
    private String SDURL;
    private String HDURL;

    private String ultraClearURL;

    private String downloadURL;

    private String htmlURL;

    private String duration;

    private String canWatchFlag;

    private String collectionFalg;

    private String lastPlayTime;

    private String dataId; //用于收藏

    public List<VideoType> getList() {
        return list;
    }

    public void setList(List<VideoType> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmoothURL() {
        return smoothURL;
    }

    public void setSmoothURL(String smoothURL) {
        this.smoothURL = smoothURL;
    }

    public String getSDURL() {
        return SDURL;
    }

    public void setSDURL(String SDURL) {
        this.SDURL = SDURL;
    }

    public String getHDURL() {
        return HDURL;
    }

    public void setHDURL(String HDURL) {
        this.HDURL = HDURL;
    }


    public String getUltraClearURL() {
        return ultraClearURL;
    }

    public void setUltraClearURL(String ultraClearURL) {
        this.ultraClearURL = ultraClearURL;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCanWatchFlag() {
        return canWatchFlag;
    }

    public void setCanWatchFlag(String canWatchFlag) {
        this.canWatchFlag = canWatchFlag;
    }

    public String getCollectionFalg() {
        return collectionFalg;
    }

    public void setCollectionFalg(String collectionFalg) {
        this.collectionFalg = collectionFalg;
    }

    public String getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(String lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public List<VideoInfo> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<VideoInfo> bannerList) {
        this.bannerList = bannerList;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getVideoUrl() {
        if (!TextUtils.isEmpty(HDURL))
            return HDURL;
        else if (!TextUtils.isEmpty(SDURL))
            return SDURL;
        else if (!TextUtils.isEmpty(smoothURL))
            return smoothURL;
        else
            return "";
    }
}
