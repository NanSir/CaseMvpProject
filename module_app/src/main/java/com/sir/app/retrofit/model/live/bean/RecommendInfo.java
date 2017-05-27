package com.sir.app.retrofit.model.live.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 推荐列表
 * Created by zhuyinan on 2017/5/26.
 */

public class RecommendInfo {

    private List<Room> room;

    //private List<Ad> ad ;

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public List<Room> getRoom() {
        return this.room;
    }

    public class Room {
        private int id;

        private String name;

        private int is_default;

        private String icon;

        private String slug;

        private String category_more;

        private int type;

        private int screen;

        @SerializedName("list")
        private List<LiveDetails> details;

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

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getSlug() {
            return this.slug;
        }

        public void setCategory_more(String category_more) {
            this.category_more = category_more;
        }

        public String getCategory_more() {
            return this.category_more;
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

        public List<LiveDetails> getDetails() {
            return details;
        }

        public void setDetails(List<LiveDetails> details) {
            this.details = details;
        }
    }

}
