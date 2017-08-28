package com.yiguohan.idouban.presenter;

import android.content.Context;
import android.widget.Toast;

import com.yiguohan.idouban.base.BasePresenter;
import com.yiguohan.idouban.bean.filmdetail.FilmDetail;
import com.yiguohan.idouban.bean.filmlive.FilmLive;
import com.yiguohan.idouban.bean.filmusbox.FilmUsBox;
import com.yiguohan.idouban.bean.top250.Root;
import com.yiguohan.idouban.viewinterface.film.IGetFilmDetailView;
import com.yiguohan.idouban.viewinterface.film.IGetFilmLiveView;
import com.yiguohan.idouban.viewinterface.film.IGetTop250View;
import com.yiguohan.idouban.viewinterface.film.IGetUsBoxView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yiguohan.
 */

public class DoubanFilmPresenter extends BasePresenter {
    public DoubanFilmPresenter(Context mContext) {
        super(mContext);
    }


    /**
     * 获取当前上映电影
     *
     * @param iGetFilmLiveView
     */
    public void getFilmLive(IGetFilmLiveView iGetFilmLiveView) {
        doubanApi.getLiveFilm()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmLive -> {
                    displayFilmLiveList(iGetFilmLiveView, filmLive);
                }, this::loadError);
    }

    public void getFilmDetail(IGetFilmDetailView iGetFilmDetailView, String id) {
        doubanApi.getFilmDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmDetail -> {
                    displayFilmDetail(iGetFilmDetailView, filmDetail);
                }, this::loadError);
    }

    public void getTop250(IGetTop250View iGetTop250View, int start, int count, boolean isLoadMore) {
        doubanApi.getTop250(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(root -> {
                    displayDoubanTop250List(iGetTop250View, root, isLoadMore);
                }, this::loadError);
    }

    public void getUsBox(IGetUsBoxView iGetUsBoxView) {
        doubanApi.getUsBox()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmUsBox -> {
                            displayUsBox(iGetUsBoxView, filmUsBox);
                        }, this::loadError
                );
    }

    private void displayFilmLiveList(IGetFilmLiveView iGetFilmLiveView, FilmLive filmLive) {
        if (filmLive == null) {
            iGetFilmLiveView.getDataFail();
        } else {
            iGetFilmLiveView.getFilmLiveSuccess(filmLive);
        }
    }

    private void displayFilmDetail(IGetFilmDetailView iGetFilmDetailView, FilmDetail filmDetail) {
        if (filmDetail == null) {
            iGetFilmDetailView.getFilmDetailFail();
        } else {
            iGetFilmDetailView.getFilmDetailSuccess(filmDetail);
        }
    }

    private void displayDoubanTop250List(IGetTop250View iGetTop250View, Root root, boolean isLoadMore) {
        iGetTop250View.getTop250Success(root, isLoadMore);
    }

    private void displayUsBox(IGetUsBoxView iGetUsBoxView, FilmUsBox filmUsBox) {
        if (filmUsBox == null) {
            iGetUsBoxView.getDataFail();
        } else {
            iGetUsBoxView.getUsBoxSuccess(filmUsBox);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络出现异常", Toast.LENGTH_SHORT).show();
    }
}
