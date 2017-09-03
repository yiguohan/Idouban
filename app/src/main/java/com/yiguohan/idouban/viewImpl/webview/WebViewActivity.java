package com.yiguohan.idouban.viewImpl.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    public static String EXTRA_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }

    @Override
    public String setActName() {
        return null;
    }
}
