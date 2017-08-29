package com.yiguohan.idouban.viewImpl.film;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.bean.filmlive.FilmLive;
import com.yiguohan.idouban.viewinterface.film.IGetFilmLiveView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmLiveFragment extends BaseFragment implements IGetFilmLiveView, SwipeRefreshLayout.OnRefreshListener {


    public FilmLiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_live, container, false);
        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getFilmLiveSuccess(FilmLive filmLive) {

    }

    @Override
    public void getDataFail() {

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
