package com.ltg263.happy_dzh.baseManager;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * 描    述：Application
 * 作    者：litongge
 * 时    间：2016/7/28 17:19
 */
public class BaseApp extends Application {
    private static Stack<Activity> activityStack;
    private static BaseApp singleton;
    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.initAppConfig(this);
        singleton=this;

    }
    // Returns the application instance
    public static BaseApp getInstance() {
        return singleton;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity){
        if(activityStack ==null){
            activityStack =new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
