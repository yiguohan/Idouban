package com.yiguohan.idouban.viewImpl.book;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.bean.book.BookRoot;
import com.yiguohan.idouban.viewinterface.book.IGetBookView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookReadingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IGetBookView {


    public BookReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_reading, container, false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getBookSuceess(BookRoot bookRoot, boolean isLoadMore) {

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
}
