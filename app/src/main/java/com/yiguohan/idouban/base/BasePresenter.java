package com.yiguohan.idouban.base;

import android.content.Context;

/**
 * Created by yiguohan.
 */

public abstract class BasePresenter {

    public Context mContext;

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }
}
