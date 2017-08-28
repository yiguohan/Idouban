package com.yiguohan.idouban.presenter;

import android.content.Context;

import com.yiguohan.idouban.base.BasePresenter;
import com.yiguohan.idouban.bean.music.MusicRoot;
import com.yiguohan.idouban.bean.music.Musics;
import com.yiguohan.idouban.viewinterface.music.IGetMusicByIdView;
import com.yiguohan.idouban.viewinterface.music.IGetMusicByTagView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yiguohan.
 */

public class DoubanMusicPresenter extends BasePresenter {

    public DoubanMusicPresenter(Context mContext) {
        super(mContext);
    }

    public void getMusicByTag(IGetMusicByTagView iGetMusicByTagView, String TAG, boolean isLoadMore) {
        doubanApi.searchMusicByTag(TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(musicRoot -> {
                    displaySearchedMusic(iGetMusicByTagView, musicRoot, isLoadMore);
                }, this::loadError);
    }

    public void getMusicById(IGetMusicByIdView iGetMusicByIdView, String id) {
        doubanApi.getMusicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(musics -> {
                    displayMusic(iGetMusicByIdView, musics);
                }, this::loadError);
    }

    private void displaySearchedMusic(IGetMusicByTagView iGetMusicByTagView, MusicRoot musicRoot, boolean isLoadMore) {
        iGetMusicByTagView.getMusicByTagSuceess(musicRoot, isLoadMore);
    }

    private void displayMusic(IGetMusicByIdView iGetMusicByIdView, Musics musics) {
        iGetMusicByIdView.getMusicSuccess(musics);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
