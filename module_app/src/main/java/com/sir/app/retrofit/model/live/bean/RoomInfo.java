package com.sir.app.retrofit.model.live.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 房间信息
 * Created by zhuyinan on 2017/5/26.
 */

public class RoomInfo {

    private int uid;

    private int no;

    private String title;

    private String nick;

    private String avatar;

    private String slug;

    private String intro;

    private String category_id;

    private String category_name;

    private int screen;

    private int view;

    private int weight;

    private int follow;

    private String last_play_at;

    private String announcement;

    private String play_at;

    private String thumb;

    private String status;

    private boolean play_status;

    private boolean forbid_status;

    private boolean police_forbid;

//    private List<Admins> admins ;
//
//    private List<Notice> notice ;

    private boolean hidden;

    private String video_quality;

    private List<RoomLines> room_lines;

    private Live live;

    private boolean is_star;

    private String special;

    private String watermark_pic;

    private int watermark;

//    private List<Hot_word> hot_word ;
//
//    private List<Rank_week> rank_week ;
//
//    private List<Rank_total> rank_total ;
//
//    private List<Rank_curr> rank_curr ;

    private King_rank king_rank;

    private int lol_status;


    public class RoomLines {

        private String name;

        private Flv flv;

        private String def_mobile;

        private String def_pc;

        private String v;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Flv getFlv() {
            return flv;
        }

        public void setFlv(Flv flv) {
            this.flv = flv;
        }

        public String getDef_mobile() {
            return def_mobile;
        }

        public void setDef_mobile(String def_mobile) {
            this.def_mobile = def_mobile;
        }

        public String getDef_pc() {
            return def_pc;
        }

        public void setDef_pc(String def_pc) {
            this.def_pc = def_pc;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public class Flv {

        @SerializedName("3")
        private Ratio ratio3;

        @SerializedName("4")
        private Ratio ratio4;

        @SerializedName("5")
        private Ratio ratio5;

        private int main_mobile;

        private int main_pc;

        public Ratio getRatio3() {
            return ratio3;
        }

        public void setRatio3(Ratio ratio3) {
            this.ratio3 = ratio3;
        }

        public Ratio getRatio4() {
            return ratio4;
        }

        public void setRatio4(Ratio ratio4) {
            this.ratio4 = ratio4;
        }

        public Ratio getRatio5() {
            return ratio5;
        }

        public void setRatio5(Ratio ratio5) {
            this.ratio5 = ratio5;
        }

        public int getMain_mobile() {
            return main_mobile;
        }

        public void setMain_mobile(int main_mobile) {
            this.main_mobile = main_mobile;
        }

        public int getMain_pc() {
            return main_pc;
        }

        public void setMain_pc(int main_pc) {
            this.main_pc = main_pc;
        }
    }

    //分辨率
    public class Ratio {

        private String name;

        private String src;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    public class Live {
        private Ws ws;

        public void setWs(Ws ws) {
            this.ws = ws;
        }

        public Ws getWs() {
            return this.ws;
        }

    }

    public class Ws {
        private String name;

        private Flv flv;

        private String def_mobile;

        private String def_pc;

        private String v;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setFlv(Flv flv) {
            this.flv = flv;
        }

        public Flv getFlv() {
            return this.flv;
        }

        public void setDef_mobile(String def_mobile) {
            this.def_mobile = def_mobile;
        }

        public String getDef_mobile() {
            return this.def_mobile;
        }

        public void setDef_pc(String def_pc) {
            this.def_pc = def_pc;
        }

        public String getDef_pc() {
            return this.def_pc;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getV() {
            return this.v;
        }

    }


    public class Main {

        private String rankType;

        private int anchorGets;

        private String anchorRank;

        private int anchorPre;

        public void setRankType(String rankType) {
            this.rankType = rankType;
        }

        public String getRankType() {
            return this.rankType;
        }

        public void setAnchorGets(int anchorGets) {
            this.anchorGets = anchorGets;
        }

        public int getAnchorGets() {
            return this.anchorGets;
        }

        public void setAnchorRank(String anchorRank) {
            this.anchorRank = anchorRank;
        }

        public String getAnchorRank() {
            return this.anchorRank;
        }

        public void setAnchorPre(int anchorPre) {
            this.anchorPre = anchorPre;
        }

        public int getAnchorPre() {
            return this.anchorPre;
        }

    }

    public class RankInfo {

        private Main main;

        public void setMain(Main main) {

            this.main = main;
        }

        public Main getMain() {
            return this.main;
        }

    }


    public class CurAnchorInfo {
        private String avatar;

        private String nick;

        private int uid;

        private int no;

        private RankInfo rankInfo;

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getNick() {
            return this.nick;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getNo() {
            return this.no;
        }

        public void setRankInfo(RankInfo rankInfo) {
            this.rankInfo = rankInfo;
        }

        public RankInfo getRankInfo() {
            return this.rankInfo;
        }

    }

    /**
     * 排行
     */
    public class Rank {

        private String avatar;

        private String nick;

        private int uid;

        private int main;

        private int no;

        private int status;

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getNick() {
            return this.nick;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setMain(int main) {
            this.main = main;
        }

        public int getMain() {
            return this.main;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getNo() {
            return this.no;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

    }

    public class King_rank {

        private CurAnchorInfo curAnchorInfo;

        private List<Rank> rank;

        public void setCurAnchorInfo(CurAnchorInfo curAnchorInfo) {
            this.curAnchorInfo = curAnchorInfo;
        }

        public CurAnchorInfo getCurAnchorInfo() {
            return this.curAnchorInfo;
        }

        public void setRank(List<Rank> rank) {
            this.rank = rank;
        }

        public List<Rank> getRank() {
            return this.rank;
        }
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public String getLast_play_at() {
        return last_play_at;
    }

    public void setLast_play_at(String last_play_at) {
        this.last_play_at = last_play_at;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getPlay_at() {
        return play_at;
    }

    public void setPlay_at(String play_at) {
        this.play_at = play_at;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPlay_status() {
        return play_status;
    }

    public void setPlay_status(boolean play_status) {
        this.play_status = play_status;
    }

    public boolean isForbid_status() {
        return forbid_status;
    }

    public void setForbid_status(boolean forbid_status) {
        this.forbid_status = forbid_status;
    }

    public boolean isPolice_forbid() {
        return police_forbid;
    }

    public void setPolice_forbid(boolean police_forbid) {
        this.police_forbid = police_forbid;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getVideo_quality() {
        return video_quality;
    }

    public void setVideo_quality(String video_quality) {
        this.video_quality = video_quality;
    }

    public List<RoomLines> getRoom_lines() {
        return room_lines;
    }

    public void setRoom_lines(List<RoomLines> room_lines) {
        this.room_lines = room_lines;
    }

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
    }

    public boolean is_star() {
        return is_star;
    }

    public void setIs_star(boolean is_star) {
        this.is_star = is_star;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getWatermark_pic() {
        return watermark_pic;
    }

    public void setWatermark_pic(String watermark_pic) {
        this.watermark_pic = watermark_pic;
    }

    public int getWatermark() {
        return watermark;
    }

    public void setWatermark(int watermark) {
        this.watermark = watermark;
    }

    public King_rank getKing_rank() {
        return king_rank;
    }

    public void setKing_rank(King_rank king_rank) {
        this.king_rank = king_rank;
    }

    public int getLol_status() {
        return lol_status;
    }

    public void setLol_status(int lol_status) {
        this.lol_status = lol_status;
    }
}
