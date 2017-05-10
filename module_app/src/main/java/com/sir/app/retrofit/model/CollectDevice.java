package com.sir.app.retrofit.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.enums.AssignType;
import com.sir.app.retrofit.common.Constant;
import com.sir.app.retrofit.model.cartoon.bean.ParentItem;
import com.sir.app.retrofit.model.video.bean.VideoRes;
import com.sir.app.retrofit.util.DateUtils;
import com.space.app.base.utils.LiteOrmDBUtils;

import java.util.List;

/**
 * 收藏
 * Created by zhuyinan on 2017/4/21.
 */

public class CollectDevice {
    @Table("tab_collect")
    public class Collect {
        //设置为主键，自增
        @PrimaryKey(AssignType.AUTO_INCREMENT)
        private int id;
        private String dataId;
        private String title;
        private String description;
        private String pic;
        private String address;
        private String type;
        private String register;
        private String date;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getRegister() {
            return register;
        }

        public void setRegister(String register) {
            this.register = register;
        }
    }


    /**
     * 收藏视频
     *
     * @param res
     */
    public void collectVideo(VideoRes res) {
        Collect bean = new Collect();
        bean.setDataId(res.getDataId());
        bean.setTitle(res.getTitle());
        bean.setDescription(res.getDescription());
        bean.setAddress(res.getVideoUrl());
        bean.setPic(res.getPic());
        bean.setDate(DateUtils.getTodayDate());
        bean.setRegister(Constant.REG_COLLECT);
        bean.setType(Constant.TYPE_VIDEO);
        LiteOrmDBUtils.insert(bean);
    }


    /**
     * 收藏漫画
     *
     * @param cartoon
     */
    public void collectCartoon(ParentItem cartoon) {
        Collect bean = new Collect();
        bean.setDataId(cartoon.getId() + "");
        bean.setTitle(cartoon.getTitle());
        bean.setDescription(cartoon.getExplain());
        bean.setPic(cartoon.getFrontCover());
        bean.setDate(DateUtils.getTodayDate());
        bean.setRegister(Constant.REG_COLLECT);
        bean.setType(Constant.TYPE_CARTOON);
        LiteOrmDBUtils.insert(bean);
    }


    /**
     * 访问记录
     *
     * @param res
     */
    public void saveRecord(VideoRes res) {
        Collect bean = new Collect();
        bean.setTitle(res.getTitle());
        bean.setDescription(res.getDescription());
        bean.setAddress(res.getVideoUrl());
        bean.setPic(res.getPic());
        bean.setDate(DateUtils.getTodayDate());
        bean.setRegister(Constant.REG_RECORD);
        bean.setType(Constant.TYPE_VIDEO);
        LiteOrmDBUtils.insert(bean);
    }


    public void operateCollect(Object object, boolean valid) {
        if (object instanceof VideoRes) {
            VideoRes res = (VideoRes) object;
            if (valid) {
                collectVideo(res);
            } else {
                LiteOrmDBUtils.deleteWhere(CollectDevice.Collect.class, "dataId", new String[]{res.getDataId()});
            }
        } else if (object instanceof ParentItem) {
            ParentItem cartoon = (ParentItem) object;
            if (valid) {
                collectCartoon(cartoon);
            } else {
                LiteOrmDBUtils.deleteWhere(CollectDevice.Collect.class, "dataId", new String[]{cartoon.getId() + ""});
            }
        } else {

        }
    }

    /**
     * 获取一条记录
     *
     * @param mediaId
     * @return
     */
    public static Collect selectCollect(String mediaId, String register, String type) {
        List<Collect> list = LiteOrmDBUtils.getLiteOrm()
                .query(new QueryBuilder<>(Collect.class)
                        .whereEquals("dataId", mediaId)
                        .whereAppendAnd()
                        .whereEquals("type", type)
                        .whereAppendAnd()
                        .whereEquals("register", register)
                );
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public static boolean isCollect(String mediaId, String type) {
        Collect collect = selectCollect(mediaId, Constant.REG_COLLECT, type);
        return collect != null ? true : false;
    }


    /**
     * 查询 收藏或者是访问记录
     *
     * @param register
     * @param type
     * @return
     */
    public static List<Collect> selectCollects(String register, String type) {
        return LiteOrmDBUtils.getLiteOrm()
                .query(new QueryBuilder<>(Collect.class)
                        .whereEquals("type", type)
                        .whereAppendAnd()
                        .whereEquals("register", register));
    }

    /**
     * 查询 收藏或者是访问记录
     *
     * @param register
     * @return
     */
    public static List<Collect> selectCollects(String register) {
        return LiteOrmDBUtils.getLiteOrm()
                .query(new QueryBuilder<>(Collect.class)
                        .whereEquals("register", register));
    }
}
