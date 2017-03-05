package com.qzct.immediatechoice;

import android.app.Application;

import com.qzct.immediatechoice.domain.User;

import org.xutils.x;

/**
 * Created by Administrator on 2017-02-27.
 */

public class MyApplication extends Application {


    public static String url = "http://123.207.31.213/ImmediateChoice_service/";
//    public static String url = "http://192.168.1.103:8080/Server/";

    public static String url_login = url + "LoginServlet";
    public static String url_Discovery = url + "DiscoveryServlet";
    public static String url_user = url + "UserServlet";
    public static String url_image_text = url + "ImageTextServlet";
    public static String url_upload = url + "UploadServlet";
    public static String url_register = url + "RegisterServlet";
    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    @Override
    public void onCreate() {
        user = new User();
        super.onCreate();
        //初始化xUtils
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    public void updateUserPortrait(User user) {


    }
}
