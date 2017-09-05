package com.yiguohan.idouban.viewImpl.film;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.FilmUsBoxAdapter;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.filmusbox.FilmUsBox;
import com.yiguohan.idouban.presenter.DoubanFilmPresenter;
import com.yiguohan.idouban.utils.ScreenUtils;
import com.yiguohan.idouban.utils.ThemeUtils;
import com.yiguohan.idouban.viewImpl.filmdetail.FilmDetailActivity;
import com.yiguohan.idouban.viewinterface.film.IGetUsBoxView;
import com.yiguohan.idouban.widget.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmGodFragment extends BaseFragment implements IGetUsBoxView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private DoubanFilmPresenter doubanFilmPresenter;
    private FilmUsBoxAdapter filmUsBoxAdapter;

    public static FilmGodFragment newInstance() {
        Bundle args = new Bundle();
        FilmGodFragment fragment = new FilmGodFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_live, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filmUsBoxAdapter = new FilmUsBoxAdapter(getActivity());
        doubanFilmPresenter = new DoubanFilmPresenter(getActivity());
        doubanFilmPresenter.getUsBox(this);
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), 0);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setAdapter(filmUsBoxAdapter);
    }

    @Override
    public void onRefresh() {
        doubanFilmPresenter.getUsBox(this);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout!=null){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },2000);
    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }

    @Override
    public void getUsBoxSuccess(FilmUsBox filmUsBox) {
        filmUsBoxAdapter.setDatas(filmUsBox.getSubjects());
        filmUsBoxAdapter.notifyDataSetChanged();
        filmUsBoxAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, Object data) {
                Intent intent = new Intent(getActivity(), FilmDetailActivity.class);
                intent.putExtra(FilmDetailActivity.EXTRA_ID, filmUsBox.getSubjects().get(positon).getSubject().getId());
                startActivityByIntent(intent);
            }
        });
    }

    @Override
    public void getDataFail() {

    }
}
