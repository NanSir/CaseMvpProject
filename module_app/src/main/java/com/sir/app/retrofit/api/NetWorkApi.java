package com.sir.app.retrofit.api;

import java.util.List;

/**
 * 网络接口
 * Created by zhuyinan on 2017/4/1.
 */

public class NetWorkApi {

    /*======================================易源数据接口============================================*/
    //APP ID
    public static final String APP_ID_YY = "34835";
    //APP KEY
    public static final String API_KEY_YY = "a7b50249ea4c47ff98bc3b579e20328d";
    //Base地址
    public static final String YH_BaseUrl = "http://route.showapi.com/";
    //获取新闻
    public static final String getNews = "1251-1";
    //获取新闻频道
    public static final String getNewsChannelList = "109-34";
    //获取新闻区域
    public static final String getNewsCityList = "170-48";
    //获取频道新闻
    public static final String getChannelNews = "109-35";
    //黑白漫话列表
    public static final String getCartoonList = "958-1";
    //黑白漫话详情
    public static final String getCartoonDetails = "958-2";

    /*========================================聚合数据接口==========================================*/
    //APP KEY 影视影讯检索
    public static final String API_KEY_JH = "eff63ec0285b079f8fe418a13778a10d";
    //Base地址
    public static final String JH_BaseUrl = "http://op.juhe.cn/";
    //最近影讯
    public static final String getRecentShadow = "onebox/movie/pmovie";


    /*========================================视频接口==========================================*/

    //Base地址
    public static final String video_BaseUrl = "http://api.svipmovie.com/front/";
    //视频首页
    public static final String video_home = "homePageApi/homePage.do";
    //视频频道
    public static final String video_colums = "columns/getVideoList.do";
    //视频搜索
    public static final String video_search = "searchKeyWordApi/getVideoListByKeyWord.do";
    //视频详情
    public static final String video_detail = "videoDetailApi/videoDetail.do";
    //获取评论
    public static final String video_commentary = "Commentary/getCommentList.do";


    /*========================================鼠绘漫画接口==========================================*/
    //Base地址
    public static final String SH_BaseUrl = "http://www.ishuhui.net/";
    //获取书籍列表http://www.ishuhui.net/ComicBooks/GetAllBook?PageIndex=0&Size=30&ClassifyId=0   分类漫画ClassifyId--0,1,2
    public static final String SH_GetAllBook = "ComicBooks/GetAllBook";
    //获取章节http://www.ishuhui.net/ComicBooks/GetChapterList?PageIndex=0&id=49 详情列表
    public static final String SH_GetChapterList = "ComicBooks/GetChapterList";
    //查看章节http://www.ishuhui.net/ReadComicBooksToIso/5411
    public static final String SH_ReadComicBooksToIso = "ReadComicBooksToIso";


    /*========================================全民直播接口==========================================*/
    //Base地址
    public static final String QM_BaseUrl = "http://www.quanmin.tv/";
    //分类列表
    public static final String QM_Category_List = "json/app/index/category/info-android.json?v=3.0.1&os=1&ver=4";
    //推荐列表
    public static final String QM_Recommend="json/app/index/recommend/list-android.json?v=3.0.1&os=1&ver=4";
    //频道信息
    public static final String QM_Live_Info="json/categories/{slug}/list.json?v=3.0.1&os=1&ver=4";
    //进入房间
    public static final String QM_Room = "json/rooms/{uid}/info.json?v=3.0.1&os=1&ver=4";
    //搜索
    public static final String QM_Search="site/search";

}
