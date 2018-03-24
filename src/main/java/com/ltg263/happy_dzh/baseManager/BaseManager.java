package com.ltg263.happy_dzh.baseManager;

import android.app.Application;
import android.os.Handler;


/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：log打印控制类
 */
public class BaseManager {

    public static BaseApp app;
    public static Handler baseHandler;
    public static void init(Application context) {
        app = (BaseApp) context;
        baseHandler = new Handler();
    }
}
