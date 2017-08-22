package com.yiguohan.idouban.base;

import android.content.Context;

/**
 * Created by yiguohan.
 */

public interface IBaseView {

    /**
     * 显示可取消的进度条
     *
     * @param message
     */
    void showProgress(String message);

    /**
     * 显示可取消的无文字进度条
     */
    void showProgress();

    /**
     * 取消进度条
     */
    void cancelProgress();

    /**
     * 根据资源文件来弹出吐司
     * @param resId
     */
    void showTheToast(int resId);

    /**
     * 弹出吐司
     *
     * @param msg
     */
    void showTheToast(String msg);

    /**
     * 获取上下文对象
     *
     * @return
     */
    Context getContext();

    void onServerFail(String msg);
}
