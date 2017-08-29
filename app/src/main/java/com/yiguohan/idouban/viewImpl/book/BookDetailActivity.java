package com.yiguohan.idouban.viewImpl.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.bean.book.Books;
import com.yiguohan.idouban.viewinterface.book.IGetBookDetailView;

public class BookDetailActivity extends BaseActivity implements IGetBookDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
    }

    @Override
    public void getBookSuccess(Books books) {

    }

    @Override
    public void getBookFail() {

    }

    @Override
    public String setActName() {
        return null;
    }
}
