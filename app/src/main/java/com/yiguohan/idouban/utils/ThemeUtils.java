package com.yiguohan.idouban.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;

import com.yiguohan.idouban.MyApp;
import com.yiguohan.idouban.R;

/**
 * Created by yiguohan.
 */

public class ThemeUtils {

    private static int defaultThemeColor = Color.rgb(251, 91, 129);
    private static Context context = MyApp.mContext;

    /**
     * 获取本机的主题颜色值
     *
     * @return
     */
    public static int getThemeColor() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE);
        if (sharedPreferences == null) {
            setThemeColor(defaultThemeColor);
        }
        return sharedPreferences.getInt("themecolor", defaultThemeColor);
    }

    /**
     * 记录本机的主体颜色值
     *
     * @param color
     */
    public static void setThemeColor(int color) {
        SharedPreferences.Editor editor = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE).edit();
        editor.putInt("themecolor", color);
        editor.commit();
    }

    public static void setThemePosition(int posision) {
        SharedPreferences.Editor editor = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE).edit();
        editor.putInt("themeposition", posision);
        editor.commit();
    }

    public static int getThemePosition() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE);
        if (sharedPreferences == null) {
            setThemePosition(0);
        }
        return sharedPreferences.getInt("themeposition", 0);
    }

    public static ColorStateList getNaviItemIconTintList() {
        int position = getThemePosition();
        Resources resources = context.getResources();
        ColorStateList csl;
        switch (position) {
            case 0:
                csl = resources.getColorStateList(R.color.theme_redbase_nav_menu_icontint);
                return csl;
            case 1:
                csl = resources.getColorStateList(R.color.theme_blue_nav_menu_icontint);
                return csl;
            case 2:
                csl = resources.getColorStateList(R.color.theme_lightblue_nav_menu_icontint);
                return csl;
            case 3:
                csl = resources.getColorStateList(R.color.theme_black_nav_menu_icontint);
                return csl;
            case 4:
                csl = resources.getColorStateList(R.color.theme_teal_nav_menu_icontint);//蓝绿色
                return csl;
            case 5:
                csl = resources.getColorStateList(R.color.theme_brown_nav_menu_icontint);
                return csl;
            case 6:
                csl = resources.getColorStateList(R.color.theme_green_nav_menu_icontint);
                return csl;
            case 7:
                csl = resources.getColorStateList(R.color.theme_red_nav_menu_icontint);
                return csl;
        }
        csl = resources.getColorStateList(R.color.theme_redbase_tablayout_text_colorlist);
        return csl;
    }
}
