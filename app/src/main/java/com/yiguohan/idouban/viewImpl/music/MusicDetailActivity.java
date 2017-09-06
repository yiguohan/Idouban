package com.yiguohan.idouban.viewImpl.music;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.bean.music.Musics;
import com.yiguohan.idouban.presenter.DoubanMusicPresenter;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.viewImpl.webview.WebViewActivity;
import com.yiguohan.idouban.viewinterface.music.IGetMusicByIdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicDetailActivity extends BaseActivity implements IGetMusicByIdView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.iv_music)
    ImageView ivMusic;
    @BindView(R.id.tv_music_name)
    TextView tvMusicName;
    @BindView(R.id.tv_music_art)
    TextView tvMusicArt;
    @BindView(R.id.tv_music_pubdate)
    TextView tvMusicPublishtime;
    @BindView(R.id.tv_music_grade)
    TextView tvMusicGrade;
    @BindView(R.id.tv_music_grade_num)
    TextView tvMusicGradeNum;
    @BindView(R.id.tv_listen)
    TextView tvListen;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_songs)
    TextView tvSongs;

    private Musics musics;

    private DoubanMusicPresenter doubanMusicPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
        ButterKnife.bind(this);
        applyKitKatTranslucency(getResources().getColor(R.color.black));
        initView();
        initData();
    }

    private void initView() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("音乐");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            doubanMusicPresenter = new DoubanMusicPresenter(this);
            if (!TextUtils.isEmpty(id))
                doubanMusicPresenter.getMusicById(this, id);
        }
    }

    @Override
    public void getMusicSuccess(Musics musics) {
        this.musics = musics;
        DisplayImgUtils.getInstance().display(this, musics.getImage(), ivMusic);
        tvMusicName.setText(musics.getTitle());
        if (musics.getAuthor() != null && musics.getAuthor().size() > 0) {
            tvMusicArt.setText("艺术家：" + musics.getAuthor().get(0).getName());
        }
        if (musics.getAttrs() != null && musics.getAttrs().getPubdate() != null && musics.getAttrs().getPubdate().size() > 0) {
            tvMusicPublishtime.setText(musics.getAttrs().getPubdate().get(0));
        }
        if (musics.getRating() != null) {
            if (!TextUtils.isEmpty(musics.getRating().getAverage())) {
                tvMusicGrade.setText(musics.getRating().getAverage());
            }
            if (!TextUtils.isEmpty(musics.getRating().getNumRaters() + "")) {
                tvMusicGradeNum.setText(musics.getRating().getNumRaters() + "人评分");
            }
        }
        if (musics.getSummary() != null) {
            tvDescription.setText(musics.getSummary());
        }
        if (musics.getAttrs().getTracks() != null && musics.getAttrs().getTracks().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String song :
                    musics.getAttrs().getTracks()) {
                stringBuilder.append(song + "/n");
            }
            tvSongs.setText(stringBuilder.toString());
        }
    }


    @Override
    public String setActName() {
        return null;
    }


    @OnClick({R.id.tv_listen, R.id.tv_more_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_listen:
            case R.id.tv_more_info:

                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.EXTRA_URL, musics.getAlt());
                startActivityByIntent(intent);
                break;
        }
    }
}
