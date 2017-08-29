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
import com.yiguohan.idouban.bean.top250.Root;
import com.yiguohan.idouban.viewinterface.film.IGetTop250View;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmTop250Fragment extends BaseFragment implements IGetTop250View, SwipeRefreshLayout.OnRefreshListener {


    public FilmTop250Fragment() {
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
    public void getTop250Success(Root root, boolean isLoadMore) {

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
