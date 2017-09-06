package com.yiguohan.idouban.utils;

import android.text.Html;
import android.widget.TextView;

/**
 * Created by yiguohan.
 */

public class ViewUtils {
    /**
     * 给TextView加下划线
     *
     * @param textView
     * @param underlineString
     */
    public static void setTextViewUnderline(TextView textView, String underlineString) {
        textView.setText(Html.fromHtml("<u>" + underlineString + "</u>"));
    }
}
