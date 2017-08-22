package com.yiguohan.idouban.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yiguohan.idouban.R;
import com.yiguohan.idouban.utils.ThemeUtils;

import java.lang.annotation.Target;

/**
 * Created by yiguohan.
 */

public abstract class BaseActivity extends BaseFragmentActivity implements IBaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 根据指定的类打开Activity
     *
     * @param pClass
     */
    protected void startActivity(Class<?> pClass) {
        Intent intent = new Intent();

        intent.setClass(this, pClass);

        startActivity(intent);

        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);//Activity的切换动画
    }

    protected void startActivityByIntent(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public void showTheToast(int resId) {

    }

    @Override
    public void showTheToast(String msg) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onServerFail(String msg) {

    }

    /**
     * 关闭Activity
     */
    public void backActivity() {
        finish();
        overridePendingTransition(R.anim.trans_pre_in, R.anim.trans_pre_out);
    }

    /**
     * 针对4.4(Api 19)以后版本的沉浸式状态栏优化
     */
    protected void applyKitKatTranslucency() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintColor(ThemeUtils.getThemeColor());
        }
    }

    protected void applyKitKatTranslucency(int res){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintColor(res);
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
