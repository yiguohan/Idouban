package com.yiguohan.idouban.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yiguohan.
 */

public class DisplayImgUtils {

    public static DisplayImgUtils instance;

    private DisplayImgUtils() {
    }

    public static DisplayImgUtils getInstance() {
        if (instance == null) {
            instance = new DisplayImgUtils();
        }
        return instance;
    }

    public void display(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

}
