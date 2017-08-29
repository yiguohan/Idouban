package com.yiguohan.idouban.viewImpl.music;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.bean.music.MusicRoot;
import com.yiguohan.idouban.viewinterface.music.IGetMusicByTagView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,IGetMusicByTagView {


    public MusicContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_content, container, false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getMusicByTagSuceess(MusicRoot musicRoot, boolean isLoadMore) {

    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }
}
