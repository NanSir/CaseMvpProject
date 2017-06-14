package com.sir.app.retrofit.common;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.net.config.NetWorkConfiguration;
import com.sir.app.retrofit.net.http.HttpUtils;
import com.space.app.base.BaseApplication;
import com.space.app.base.utils.LiteOrmDBUtils;

import org.polaric.colorful.Colorful;

import im.fir.sdk.FIR;

/**
 * 整个工程Applicaiton
 * Created by zhuyinan on 2017/3/28.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化内存检查
        //LeakCanary.install(this);
        // BlockCanary.install(this, new AppBlockCanaryContext()).start();

        initOkHttpUtils();
        initDBUtils();
        initColorful();
        FIR.init(this);

        //日志收集
        Thread.setDefaultUncaughtExceptionHandler(ErrorLogCollector.getInstance().getUncaughtException());
    }

    /**
     * 初始化网络配置
     */
    private void initOkHttpUtils() {
        NetWorkConfiguration configuration = new NetWorkConfiguration(this)
                .baseUrl(NetWorkApi.YH_BaseUrl)
                .isCache(true)
                .isDiskCache(true)
                .isMemoryCache(true);
        HttpUtils.setConFiguration(configuration);
    }

    /**
     * 初始化持久化数据库
     */
    private void initDBUtils() {
        LiteOrmDBUtils.createDb(this, "case");
    }


    /**
     * 初始化颜色主题选择
     */
    private void initColorful() {
//        Colorful.defaults()
//                .primaryColor(Colorful.ThemeColor.DARK)
//                .accentColor(Colorful.ThemeColor.DEEP_ORANGE)
//                .translucent(false);
//                //.night(false);
        Colorful.init(this);
    }


}
