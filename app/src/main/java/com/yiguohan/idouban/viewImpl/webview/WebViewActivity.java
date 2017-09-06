package com.yiguohan.idouban.viewImpl.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String url;
    public static String EXTRA_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        applyKitKatTranslucency();
        initData();
        initView();

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null)
            url = intent.getStringExtra(EXTRA_URL);
    }

    private void initView() {
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                toolbar.setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public String setActName() {
        return null;
    }
}
