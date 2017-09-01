package com.yiguohan.idouban.viewImpl.book;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.BookReadingAdapter;
import com.yiguohan.idouban.api.BookApiUtils;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.bean.book.BookRoot;
import com.yiguohan.idouban.bean.book.Books;
import com.yiguohan.idouban.presenter.DoubanBookPresenter;
import com.yiguohan.idouban.utils.ScreenUtils;
import com.yiguohan.idouban.utils.ThemeUtils;
import com.yiguohan.idouban.viewinterface.book.IGetBookView;
import com.yiguohan.idouban.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookReadingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IGetBookView {

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int position;
    private BookReadingAdapter adapter;
    private List<Books> booksList;
    private List<String> listTag;
    private DoubanBookPresenter doubanBookPresenter;
    private LinearLayoutManager mLayoutManager;
    private int lastVisibleItem;


    public static BookReadingFragment newInstance(int position, String title) {
        Bundle arg = new Bundle();
        arg.putInt("position", position);
        arg.putString("title", title);
        BookReadingFragment fragment = new BookReadingFragment();
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_reading, container, false);
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
        booksList = new ArrayList<>();
        String[] strTags = BookApiUtils.getApiTag(position);
        listTag = Arrays.asList(strTags);
        scrollRecyclerView();
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);
        doubanBookPresenter = new DoubanBookPresenter(getActivity());
        String tag = BookApiUtils.getRandomTag(listTag);

        doubanBookPresenter.searchBookByTag(this, tag, false);
        adapter = new BookReadingAdapter(getActivity());
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        //添加Item之间的装饰
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), 0);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        listTag = Arrays.asList(BookApiUtils.getApiTag(position));
        String tag = BookApiUtils.getRandomTag(listTag);
        doubanBookPresenter.searchBookByTag(BookReadingFragment.this, tag, false);
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
    public void getBookSuceess(BookRoot bookRoot, boolean isLoadMore) {
        if (isLoadMore) {
            booksList.addAll(bookRoot.getBooks());

        } else {
            booksList.clear();
            booksList.addAll(bookRoot.getBooks());
        }
        adapter.setList(booksList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getBookFail() {

    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }

    public void scrollRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                    if (mLayoutManager.getItemCount()==1){
                        if (adapter!=null){
                            adapter.updateLoadStaues(adapter.LOAD_NONE);
                        }
                        return;
                    }
                }
                if (lastVisibleItem+1 ==mLayoutManager.getItemCount()){
                    if (adapter!=null){
                        adapter.updateLoadStaues(adapter.LOAD_PULL_TO);
                        adapter.updateLoadStaues(adapter.LOAD_MORE);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String tag = BookApiUtils.getRandomTag(listTag);
                            doubanBookPresenter.searchBookByTag(BookReadingFragment.this,tag,true);
                        }
                    },1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
}
