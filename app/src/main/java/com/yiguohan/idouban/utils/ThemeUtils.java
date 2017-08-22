package com.yiguohan.idouban.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.yiguohan.idouban.MyApp;

/**
 * Created by yiguohan.
 */

public class ThemeUtils {

    private static int defaultThemeColor = Color.rgb(251,91,129);
    private static Context context = MyApp.mContext;

    public static int getThemeColor(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("themecolor",defaultThemeColor);
    }
}
