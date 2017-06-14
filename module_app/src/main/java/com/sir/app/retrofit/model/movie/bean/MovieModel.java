package com.sir.app.retrofit.model.movie.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class MovieModel implements Serializable {

    @SerializedName("tvTitle")
    private String name;

    @SerializedName("iconaddress")
    private String poster;

    private String grade;

    private String gradeNum;

    @SerializedName("iconlinkUrl")
    private String webUrl;

    @SerializedName("subHead")
    private String cinemaNum;

    @SerializedName("playDate")
    private PlayDate releaseDate;

    private LabelType director;

    @SerializedName("star")
    private LabelType cast;

    @SerializedName("type")
    private LabelType movieType;

    @SerializedName("story")
    private Story story;

    @SerializedName("more")
    private More more;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getCinemaNum() {
        return cinemaNum;
    }

    public void setCinemaNum(String cinemaNum) {
        this.cinemaNum = cinemaNum;
    }

    public PlayDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(PlayDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LabelType getDirector() {
        return director;
    }

    public void setDirector(LabelType director) {
        this.director = director;
    }

    public LabelType getCast() {
        return cast;
    }

    public void setCast(LabelType cast) {
        this.cast = cast;
    }

    public LabelType getMovieType() {
        return movieType;
    }

    public void setMovieType(LabelType movieType) {
        this.movieType = movieType;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public More getMore() {
        return more;
    }

    public void setMore(More more) {
        this.more = more;
    }

    public String getMovieTypeString() {
        return extractLabelTypeString(getMovieType());
    }

    public List<String> getMovieTypeList() {
        List<String> list = null;
        LabelType labelType = getMovieType();
        if (labelType.getData() != null) {
            list = new ArrayList<>();
            if (labelType.getData().getLabel1() != null) {
                list.add(labelType.getData().getLabel1().getName());
            }
            if (labelType.getData().getLabel2() != null) {
                list.add(labelType.getData().getLabel2().getName());
            }
            if (labelType.getData().getLabel3() != null) {
                list.add(labelType.getData().getLabel3().getName());
            }
            if (labelType.getData().getLabel4() != null) {
                list.add(labelType.getData().getLabel4().getName());
            }
        }

        return list;
    }

    public String getCastString() {
        return extractLabelTypeString(getCast());
    }

    public String getReleaseDateString() {
        if (getReleaseDate() == null) {
            return null;
        }
        return getReleaseDate().getData();
    }

    private String extractLabelTypeString(LabelType labelType) {
        if (labelType == null) return "";

        StringBuilder builder = new StringBuilder();
        if (labelType.getData() != null) {
            if (labelType.getData().getLabel1() != null) {
                builder.append(labelType.getData().getLabel1().getName()).append("，");
            }
            if (labelType.getData().getLabel2() != null) {
                builder.append(labelType.getData().getLabel2().getName()).append("，");
            }
            if (labelType.getData().getLabel3() != null) {
                builder.append(labelType.getData().getLabel3().getName()).append("，");
            }
            if (labelType.getData().getLabel4() != null) {
                builder.append(labelType.getData().getLabel4().getName()).append("，");
            }
            if (builder.toString().endsWith("，")) {
                builder.deleteCharAt(builder.lastIndexOf("，"));
            }
        }

        return builder.toString();
    }
}