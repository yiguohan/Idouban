package com.yiguohan.idouban.viewImpl.filmdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.bean.filmdetail.FilmDetail;
import com.yiguohan.idouban.viewinterface.film.IGetFilmDetailView;

public class FilmDetailActivity extends BaseActivity implements IGetFilmDetailView {


    public static String EXTRA_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
    }

    @Override
    public void getFilmDetailSuccess(FilmDetail filmDetail) {

    }

    @Override
    public void getFilmDetailFail() {

    }

    @Override
    public String setActName() {
        return null;
    }
}
