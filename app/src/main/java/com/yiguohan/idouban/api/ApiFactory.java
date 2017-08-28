package com.yiguohan.idouban.api;

/**
 * Created by yiguohan.
 */

public class ApiFactory {

    protected static final Object monitor = new Object();

    static DoubanApi doubanApiSingleton = null;

    public static DoubanApi getDoubanApiSingleton() {
        synchronized (monitor) {
            if (doubanApiSingleton == null) {
                doubanApiSingleton = new ApiRetrofit().getDoubanApiService();
            }
            return doubanApiSingleton;
        }
    }
}
