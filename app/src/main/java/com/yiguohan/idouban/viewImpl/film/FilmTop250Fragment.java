package com.yiguohan.idouban.viewImpl.film;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.Top250FilmAdapter;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.bean.top250.Root;
import com.yiguohan.idouban.presenter.DoubanFilmPresenter;
import com.yiguohan.idouban.utils.ThemeUtils;
import com.yiguohan.idouban.viewinterface.film.IGetTop250View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmTop250Fragment extends BaseFragment implements IGetTop250View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private DoubanFilmPresenter doubanFilmPresenter;
    private Top250FilmAdapter adapter;

    private LinearLayoutManager mLayoutManager;

    private int lastVisiableItem;
    private int pageCount;
    private final int PAGE_SIZE = 10;
    private Root mRoot;

    public static FilmTop250Fragment newInstance() {
        Bundle args = new Bundle();
        FilmTop250Fragment filmTop250Fragment = new FilmTop250Fragment();
        filmTop250Fragment.setArguments(args);
        return filmTop250Fragment;
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
        doubanFilmPresenter = new DoubanFilmPresenter(getActivity());
        doubanFilmPresenter.getTop250(this, PAGE_SIZE * pageCount, PAGE_SIZE, false);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        scrollRecyclerView();
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void scrollRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisiableItem = mLayoutManager.findLastVisibleItemPosition();
                    if (mLayoutManager.getItemCount() == 1) {
                        if (adapter != null) {
                            adapter.updateLoadStatus(adapter.LOAD_MORE);
                        }
                        return;
                    }
                }
                if (lastVisiableItem + 1 == mLayoutManager.getItemCount()) {
                    if (adapter != null) {
                        adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pageCount++;
                            doubanFilmPresenter.getTop250(FilmTop250Fragment.this, PAGE_SIZE * pageCount, PAGE_SIZE, true);
                        }
                    }, 1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiableItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRefresh() {
        doubanFilmPresenter.getTop250(this, PAGE_SIZE * pageCount, PAGE_SIZE, false);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
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
