package org.gank.util;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * 日志工具类
 * Created by Nick on 2017/1/10
 */
public class LogUtil {
    private static final String TAG = "ganklog";//日志TAG
    private static boolean isDebug = true;

    public static void init(boolean isDebug){
        LogUtil.isDebug = isDebug;
        init();
    }

    public static void init() {
        Logger.init(TAG)                 // default PRETTYLOGGER or use just init()
                .methodCount(0)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(isDebug ? LogLevel.FULL : LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(0);                // default 0
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    public static void w(String msg) {
        Logger.w(msg);
    }

    public static void w(String tag, String msg) {
        Logger.t(tag).w(msg);
    }

    public static void v(String msg) {
        Logger.v(msg);
    }

    public static void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    public static void wtf(String msg) {
        Logger.wtf(msg);
    }

    public static void wtf(String tag, String msg) {
        Logger.t(tag).wtf(msg);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void json(String tag, String msg) {
        Logger.t(tag).json(msg);
    }

    public static void xml(String xml) {
        Logger.xml(xml);
    }

    public static void xml(String tag, String msg) {
        Logger.t(tag).xml(msg);
    }

    public static void log(int priority, String tag, String msg, Throwable throwable) {
        Logger.log(priority, tag, msg, throwable);
    }


}
