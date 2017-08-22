package com.yiguohan.idouban;

import android.app.Application;
import android.content.Context;

/**
 * Created by yiguohan.
 */

public class MyApp extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
