package com.yiguohan.idouban.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by yiguohan
 */

public class ActivityCollector {

    private static Stack<Activity> activityStack;

    private static ActivityCollector instance;

    private ActivityCollector() {
    }

    public void refreshAllActivity() {
        for (Activity activity :
                activityStack) {
            activity.recreate();
        }
    }

    /**
     * ActivityCollector 单例模式
     *
     * @return ActivityCollector单例
     */
    public static ActivityCollector getInstance() {
        if (instance == null) {
            instance = new ActivityCollector();
        }
        return instance;
    }

    /**
     * 向栈内添加实例
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * activity 实例出栈
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 释放销毁指定的activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 释放销毁所有的Activity并清理Stack
     */
    public void finishAllActivity() {
        for (Activity activity :
                activityStack) {
            if (activity != null) {
                finishActivity(activity);
            }
        }
        activityStack.clear();
    }
}
