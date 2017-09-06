package com.yiguohan.idouban.viewImpl.other;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.utils.ViewUtils;
import com.yiguohan.idouban.viewImpl.webview.WebViewActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthorInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;
    @BindView(R.id.tv_github)
    TextView tvGitHub;
    @BindView(R.id.tv_blog)
    TextView tvBlog;
    @BindString(R.string.blog)
    String blog;
    @BindString(R.string.github)
    String github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_info);
        ButterKnife.bind(this);
        applyKitKatTranslucency(getResources().getColor(R.color.black));
        initToolBar();
    }

    private void initToolBar() {
        collapsingToolbarLayout.setTitle("Title");
        ViewUtils.setTextViewUnderline(tvGitHub, github);
        ViewUtils.setTextViewUnderline(tvBlog, blog);
    }

    @Override
    public String setActName() {
        return null;
    }

    @OnClick({R.id.tv_github, R.id.tv_blog})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_github:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.EXTRA_URL, github);
                startActivityByIntent(intent);
                break;
            case R.id.tv_blog:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.EXTRA_URL, blog);
                startActivityByIntent(intent);
                break;
        }
    }
}
