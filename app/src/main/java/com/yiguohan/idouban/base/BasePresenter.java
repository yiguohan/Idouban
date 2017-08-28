package com.yiguohan.idouban.base;

import android.content.Context;

import com.yiguohan.idouban.api.ApiFactory;
import com.yiguohan.idouban.api.ApiRetrofit;
import com.yiguohan.idouban.api.DoubanApi;

/**
 * Created by yiguohan.
 */

public abstract class BasePresenter {

    public Context mContext;

    public static final DoubanApi doubanApi = ApiFactory.getDoubanApiSingleton();

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }
}
