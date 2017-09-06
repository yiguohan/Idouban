package com.yiguohan.idouban.viewImpl.music;


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

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.MusicAdapter;
import com.yiguohan.idouban.api.BookApiUtils;
import com.yiguohan.idouban.api.MusicApiUtils;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.bean.music.MusicRoot;
import com.yiguohan.idouban.bean.music.Musics;
import com.yiguohan.idouban.presenter.DoubanMusicPresenter;
import com.yiguohan.idouban.utils.ThemeUtils;
import com.yiguohan.idouban.viewinterface.music.IGetMusicByTagView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IGetMusicByTagView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int position;
    private MusicAdapter adapter;
    private int lastVisiableItem;
    private LinearLayoutManager mLayoutManager;
    private DoubanMusicPresenter doubanMusicPresenter;
    private List<String> listTag;
    private List<Musics> musicList;


    public static MusicContentFragment newInstance(int position, String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("position", position);
        MusicContentFragment fragment = new MusicContentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt("position");
        }
        musicList = new ArrayList<>();
        String[] strTags = MusicApiUtils.getApiTag(position);
        listTag = Arrays.asList(strTags);
        scrollRecyclerView();
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);
        doubanMusicPresenter = new DoubanMusicPresenter(getActivity());
        String tag = BookApiUtils.getRandomTag(listTag);
        doubanMusicPresenter.getMusicByTag(this, tag, false);
        adapter = new MusicAdapter(getActivity());
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void scrollRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisiableItem = mLayoutManager.findLastVisibleItemPosition();
                    if (mLayoutManager.getItemCount() == 1) {
                        if (adapter != null) {
                            adapter.updateLoadStatus(adapter.LOAD_NONE);
                        }
                        return;
                    }
                    if (lastVisiableItem + 1 == mLayoutManager.getItemCount()) {
                        if (adapter != null) {
                            adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                            adapter.updateLoadStatus(adapter.LOAD_MORE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String tag = BookApiUtils.getRandomTag(listTag);
                                doubanMusicPresenter.getMusicByTag(MusicContentFragment.this, tag, true);
                            }
                        }, 1000);
                    }
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
        String tag = BookApiUtils.getRandomTag(listTag);
        doubanMusicPresenter.getMusicByTag(MusicContentFragment.this, tag, false);
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
    public void getMusicByTagSuceess(MusicRoot musicRoot, boolean isLoadMore) {
        if (isLoadMore) {
            musicList.addAll(musicRoot.getMusics());

        } else {
            musicList.clear();
            musicList.addAll(musicRoot.getMusics());
        }
        adapter.setList(musicList);
        adapter.notifyDataSetChanged();
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
