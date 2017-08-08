##-----------基本配置-不能被混淆的------------
-optimizationpasses 5                                                           # 指定代码的压缩级别
-dontusemixedcaseclassnames                                                     # 是否使用大小写混合
-dontskipnonpubliclibraryclasses                                                # 是否混淆第三方jar
-dontpreverify                                                                  # 混淆时是否做预校验
-verbose                                                                        # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

-keep public class * extends android.app.Activity                               # 保持Android类不被混淆
-keep public class * extends android.app.Application                            # 保持Android类不被混淆
-keep public class * extends android.app.Service                                # 保持Android类不被混淆
-keep public class * extends android.content.BroadcastReceiver                  # 保持Android类不被混淆
-keep public class * extends android.content.ContentProvider                    # 保持Android类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper               # 保持Android类不被混淆
-keep public class * extends android.preference.Preference                      # 保持Android类不被混淆
-keep public class com.android.vending.licensing.ILicensingService              # 保持Android类不被混淆

-keepclasseswithmembernames class * {                                           # 保持 native 方法不被混淆
    native <methods>;
}

-keepclasseswithmembers class * {                                               # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);     # 保持自定义控件类不被混淆
}

-keepclassmembers class * extends android.app.Activity {                        # 保持自定义控件类不被混淆
   public void *(android.view.View);
}

-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}

-keepnames class * implements java.io.Serializable                              #保持 Serializable 不被混淆


#support.v4/v7包不混淆
-keep class android.support.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep interface android.support.v7.app.** { *; }
-dontwarn android.support.**    # 忽略警告

##-----------第三方jar包library混淆配置------------


#引用的第三方jar包library
#-libraryjars libs/pldroid-player-1.5.0.jar

#pldroid-player-1.5.0.jar
-dontwarn com.pili.pldroid.player.**
-keep class com.pili.pldroid.player.** { *; }
-dontwarn tv.danmaku.ijk.media.player.**
-keep class tv.danmaku.ijk.media.player.** {*;}

#lite-orm-1.9.2.jar
-dontwarn com.litesuits.orm.**
-keep class com.litesuits.orm.** { *; }


#Imageloader
-dontwarn com.nostra13.universalimageloader.**
-keep class com.nostra13.universalimageloader.** { *; }

#Butter Knife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#Gson
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
# 下面替换成自己的实体类
-keep class com.sir.app.retrofit.model.cartoon.bean.** { *; }
-keep class com.sir.app.retrofit.model.live.bean.** { *; }
-keep class com.sir.app.retrofit.model.movie.bean.** { *; }
-keep class com.sir.app.retrofit.model.news.bean.** { *; }
-keep class com.sir.app.retrofit.model.video.bean.** { *; }

#MVP模式
-keep class * implements com.sir.app.retrofit.base.BaseModel               #保持 BaseModel 不被混淆

-keep class * implements com.sir.app.retrofit.base.Presenter               #保持 Presenter 不被混淆

-keep class * implements com.sir.app.retrofit.base.BaseView                #保持 BaseView 不被混淆


#OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#Ultra-Pull-To-Refresh下拉刷新和上拉加载的框架
-dontwarn in.srain.cube.views.ptr.**
-keep class in.srain.cube.views.ptr.**{ *;}

#HTextView动画库
-dontwarn com.hanks.htextview.**
-keep class com.hanks.htextview.**{ *;}

#SweetAlert
-dontwarn cn.pedant.SweetAlert.**
-keep class cn.pedant.SweetAlert.**{ *;}

#ImageViews动画效果
-dontwarn com.flaviofaria.kenburnsview.**
-keep class com.flaviofaria.kenburnsview.**{ *;}

#蒲公英
#-libraryjars libs/pgyer_sdk_2.7.0.jar
-dontwarn com.pgyersdk.**
-keep class com.pgyersdk.** { *; }