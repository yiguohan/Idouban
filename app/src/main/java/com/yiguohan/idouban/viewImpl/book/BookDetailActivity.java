package com.yiguohan.idouban.viewImpl.book;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.bean.book.Books;
import com.yiguohan.idouban.presenter.DoubanBookPresenter;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.viewImpl.webview.WebViewActivity;
import com.yiguohan.idouban.viewinterface.book.IGetBookDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements IGetBookDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_book_name)
    TextView tvBookName;
    @BindView(R.id.tv_book_grade)
    TextView tvBookGrade;
    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;
    @BindView(R.id.tv_book_publish_time)
    TextView tvBookPublishTime;
    @BindView(R.id.tv_book_publish_address)
    TextView tvBookPublishAddress;
    @BindView(R.id.tv_book_grade_num)
    TextView tvBookGradeNum;
    @BindView(R.id.tv_want_read)
    TextView tvWantRead;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.tv_author_decription)
    TextView tvAuthorDescription;
    @BindView(R.id.iv_description)
    TextView tvDescription;
    @BindView(R.id.tv_chapter)
    TextView tvChapter;
    @BindView(R.id.rl_author)
    RelativeLayout relativeLayout;

    private Books book;
    private DoubanBookPresenter doubanBookPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 先初始化数据，数据加载成功后在回调函数中加载View
     */
    private void initData() {
        doubanBookPresenter = new DoubanBookPresenter(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
        toolbar.setTitle("图书详情");
        //获取传递过来的信息
        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            if (!TextUtils.isEmpty(id)) {
                doubanBookPresenter.searchBookById(this, id);
            }
        }
    }

    /**
     * 在数据加载成功后的回调函数getBookSuccess中初试化View
     */
    public void initViews() {

        if (book == null) {
            return;
        }
        if (book.getImages() != null) {
            DisplayImgUtils.newInstance().display(this, book.getImages().getLarge(), ivCover);
        }
        if (!TextUtils.isEmpty(book.getTitle())) {
            tvBookName.setText(book.getTitle());
        }
        if (book.getAuthor() != null && book.getAuthor().size() > 0) {
            tvBookAuthor.setText(book.getAuthor().get(0));
        }
        if (!TextUtils.isEmpty(book.getPublisher())) {
            tvBookPublishAddress.setText(book.getPublisher());
        }
        if (!TextUtils.isEmpty(book.getPubdate())) {
            tvBookPublishTime.setText("出版时间：" + book.getPublisher());
        }
        if (!TextUtils.isEmpty(book.getSummary())) {
            tvDescription.setText(book.getSummary());
        }
        if (!TextUtils.isEmpty(book.getAuthor_intro())) {
            tvAuthorDescription.setText(book.getAuthor_intro());
        }
        if (!TextUtils.isEmpty(book.getCatalog())) {
            tvChapter.setText(book.getCatalog());
        }
        if (!TextUtils.isEmpty(book.getRating().getAverage())) {
            tvBookGrade.setText(book.getRating().getAverage() + "分");
        }
        if (!TextUtils.isEmpty(book.getRating().getNumRaters() + "")) {
            tvBookGradeNum.setText(book.getRating().getNumRaters() + "人评分");
        }

    }

    @OnClick({R.id.tv_want_read, R.id.tv_more_info, R.id.rl_author})
    public void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_want_read:
            case R.id.tv_more_info:

                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.EXTRA_URL, book.getAlt());
                startActivityByIntent(intent);
                break;
            case R.id.rl_author:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.EXTRA_URL, book.getAlt());
                startActivityByIntent(intent);
                break;
        }

    }

    @Override
    public void getBookSuccess(Books books) {
        if (books != null) {
            this.book = books;
            initViews();
        }
    }

    @Override
    public void getBookFail() {

    }

    @Override
    public String setActName() {
        return null;
    }
}
