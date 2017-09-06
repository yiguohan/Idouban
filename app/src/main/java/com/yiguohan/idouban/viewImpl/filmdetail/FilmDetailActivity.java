package com.yiguohan.idouban.viewImpl.filmdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.CastAdapter;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.filmdetail.FilmDetail;
import com.yiguohan.idouban.bean.filmdetail.FilmPeople;
import com.yiguohan.idouban.bean.top250.Casts;
import com.yiguohan.idouban.bean.top250.Directors;
import com.yiguohan.idouban.presenter.DoubanFilmPresenter;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.utils.ThemeUtils;
import com.yiguohan.idouban.viewImpl.webview.WebViewActivity;
import com.yiguohan.idouban.viewinterface.film.IGetFilmDetailView;
import com.yiguohan.idouban.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FilmDetailActivity extends BaseActivity implements IGetFilmDetailView {


    public static String EXTRA_ID = "id";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_film)
    ImageView ivFilm;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_rating_num)
    TextView tvRatingNum;
    @BindView(R.id.tv_date_and_film_time)
    TextView tvDateAndFilmTime;
    @BindView(R.id.tv_film_type)
    TextView tvFilmType;
    @BindView(R.id.tv_film_country)
    TextView tvFilmCountry;
    @BindView(R.id.tv_film_name)
    TextView tvFilmName;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;

    private String id;
    private String alt;
    private Context context;
    private DoubanFilmPresenter doubanFilmPresenter;
    private List<FilmPeople> filmPeoples = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.bind(this);
        context = this;
        applyKitKatTranslucency();
        initView();
        initData();

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
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra(EXTRA_ID);
        }
        if (!TextUtils.isEmpty(id)) {
            doubanFilmPresenter = new DoubanFilmPresenter(context);
            doubanFilmPresenter.getFilmDetail(this, id);
        }
    }

    @Override
    public String setActName() {
        return null;
    }


    @Override
    public void getFilmDetailSuccess(FilmDetail filmDetail) {
        if (filmDetail.getImages() != null && filmDetail.getImages().getLarge() != null) {
            DisplayImgUtils.getInstance().display(context, filmDetail.getImages().getLarge(), ivFilm);
        }
        if (!TextUtils.isEmpty(filmDetail.getTitle())) {
            toolbar.setTitle(filmDetail.getTitle());
        }
        if (filmDetail.getRating() != null) {
            tvRating.setText("评分：" + filmDetail.getRating().getAverage());
        }
        tvRatingNum.setText(filmDetail.getRatings_count() + "人评分");
        tvDateAndFilmTime.setText(filmDetail.getYear() + "出品");
        if (filmDetail.getCountries() != null && filmDetail.getCountries().size() > 0) {
            tvFilmCountry.setText(filmDetail.getCountries().get(0));
        }
        if (filmDetail.getGenres() != null && filmDetail.getGenres().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s :
                    filmDetail.getGenres()) {
                stringBuilder.append(s + "/");
            }
            tvFilmType.setText(stringBuilder.substring(0, stringBuilder.length() - 1));
        }
        tvDescription.setText(filmDetail.getSummary());
        tvFilmName.setText(filmDetail.getOriginal_title() + "[原名]");
        initFilmData(filmDetail);
        CastAdapter castAdapter = new CastAdapter(context);
        castAdapter.setDatas(filmPeoples);
        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(this);
        recyclerView.setLayoutManager(fullyLinearLayoutManager);
        recyclerView.setAdapter(castAdapter);
        alt = filmDetail.getAlt();
        castAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, Object data) {
                Intent intent = new Intent(FilmDetailActivity.this, WebViewActivity.class);
                String alt = filmPeoples.get(positon).getAlt();
                intent.putExtra(WebViewActivity.EXTRA_URL, alt);
                startActivityByIntent(intent);
            }
        });
    }

    /**
     * 把Director和Cast转换成FilmPeople
     *
     * @param filmDetail
     */
    private void initFilmData(FilmDetail filmDetail) {
        if (filmDetail.getDirectors() != null && filmDetail.getDirectors().size() > 0) {
            for (Directors d : filmDetail.getDirectors()) {
                FilmPeople f = new FilmPeople();
                f.setName(d.getName());
                f.setAlt(d.getAlt());
                f.setAvatars(d.getAvatars());
                f.setType(1);
                f.setId(d.getId());
                filmPeoples.add(f);
            }
        }

        if (filmDetail.getCasts() != null && filmDetail.getCasts().size() > 0) {
            for (Casts c : filmDetail.getCasts()) {
                FilmPeople f = new FilmPeople();
                f.setId(c.getId());
                f.setType(2);
                f.setAvatars(c.getAvatars());
                f.setAlt(c.getAlt());
                f.setName(c.getName());
                filmPeoples.add(f);
            }
        }
    }

    @Override
    public void getFilmDetailFail() {

    }


    @OnClick({R.id.iv_film, R.id.tv_more_info})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_film:
            case R.id.tv_more_info:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.EXTRA_URL, alt);
                startActivityByIntent(intent);
                break;
        }
    }


}
