package com.yiguohan.idouban.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yiguohan.idouban.widget.view.StatusBarView;

/**
 * Created by yiguohan.
 */

public class StatusBarUtil {

    /**
     * 针对根布局是CoordinatorLayout,使状态栏半透明
     * <p>
     * 适用于图片作为背景的界面，此时需要图片填充到状态栏
     *
     * @param activity       需要设置的Activity
     * @param statusBarAlpha 目标透明度
     */
    public static void setTranslucentForCoordinatorLayout(Activity activity, int statusBarAlpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
        addTranslucentView(activity, statusBarAlpha);
    }

    /**
     * 为DrawerLayout设置状态栏颜色，纯色
     *
     * @param activity     需要设置的activity
     * @param drawerLayout 需要设置的DrawerLayout
     * @param color        状态栏颜色值
     */
    public static void setColorNoTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color) {
        setColorForDrawerLayout(activity, drawerLayout, color, 0);
    }

    /**
     * 使状态栏透明
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static void addTranslucentView(Activity activity, int statusBarAlpha) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        if (contentView.getChildCount() > 1) {
            contentView.getChildAt(1).setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        } else {
            contentView.addView(createTranslucentStatusBarView(activity, statusBarAlpha));
        }
    }

    /**
     * 创建半透明矩形View
     *
     * @param activity       所在activity
     * @param statusBarAlpha 透明值
     * @return 半透明View
     */
    private static StatusBarView createTranslucentStatusBarView(Activity activity, int statusBarAlpha) {
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        return statusBarView;
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        //寻找资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色
     *
     * @param activity      所在Activity
     * @param drawerLayout  所在DrawerLayout
     * @param color         指定的颜色值
     * @param stausBarAlpha 指定的透明值
     */
    public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color, int stausBarAlpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //生成一个状态栏大小的矩形
        //添加一个StatusBarView进去
        ViewGroup contentLayout = (ViewGroup) drawerLayout.getChildAt(0);
        if (contentLayout.getChildCount() > 0 && contentLayout.getChildAt(0) instanceof StatusBarView) {
            contentLayout.getChildAt(0).setBackgroundColor(caculateStatusColor(color, stausBarAlpha));
        } else {
            StatusBarView statusBarView = createStatusBarView(activity, color);
            contentLayout.addView(statusBarView, 0);
        }
        //内容布局不是LinearLayout 设置 padding top
        if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
            contentLayout.getChildAt(1).setPadding(contentLayout.getPaddingLeft(), contentLayout.getPaddingTop() + getStatusBarHeight(activity), contentLayout.getPaddingRight(), contentLayout.getPaddingBottom());
        }
        //设置属性
        ViewGroup drawer = (ViewGroup) drawerLayout.getChildAt(1);
        drawerLayout.setFitsSystemWindows(false);
        contentLayout.setFitsSystemWindows(false);
        //TODO:setClipToPadding
        contentLayout.setClipToPadding(true);
        drawer.setFitsSystemWindows(true);

        addTranslucentView(activity, stausBarAlpha);
    }

    /**
     * 计算状态栏颜色
     *
     * @param color 颜色值
     * @param alpha 透明值
     * @return 最终的状态栏颜色
     */
    private static int caculateStatusColor(@ColorInt int color, int alpha) {
        //TODO 研究caculateStatusColor
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * 生成一个和状态栏大小相同的彩色矩形条
     *
     * @param activity 所在Activity
     * @param color    指定的颜色值
     * @return 生成的StatusBarView
     */
    private static StatusBarView createStatusBarView(Activity activity, @ColorInt int color) {
        //绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }
}
