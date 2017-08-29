package com.yiguohan.idouban.viewImpl.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.bean.music.Musics;
import com.yiguohan.idouban.viewinterface.music.IGetMusicByIdView;

public class MusicDetailActivity extends BaseActivity implements IGetMusicByIdView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
    }

    @Override
    public void getMusicSuccess(Musics musics) {

    }

    @Override
    public String setActName() {
        return null;
    }
}
