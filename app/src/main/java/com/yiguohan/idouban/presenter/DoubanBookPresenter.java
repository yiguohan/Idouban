package com.yiguohan.idouban.presenter;

import android.content.Context;
import android.widget.Toast;

import com.yiguohan.idouban.base.BasePresenter;
import com.yiguohan.idouban.bean.book.BookRoot;
import com.yiguohan.idouban.bean.book.Books;
import com.yiguohan.idouban.viewinterface.book.IGetBookDetailView;
import com.yiguohan.idouban.viewinterface.book.IGetBookView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yiguohan.
 */

public class DoubanBookPresenter extends BasePresenter {

    public DoubanBookPresenter(Context mContext) {
        super(mContext);
    }

    public void searchBookByTag(IGetBookView view, String TAG, boolean isLoadMore) {
        doubanApi.searchBookByTag(TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookRoot -> {
                    displaySearchedBook(view, bookRoot, isLoadMore);
                }, this::loadError);

    }

    public void searchBookById(IGetBookDetailView iGetBookDetailView, String id) {
        doubanApi.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                    displayBookDetail(iGetBookDetailView, books);
                }, this::loadError);
    }

    private void displayBookDetail(IGetBookDetailView view, Books books) {
        view.getBookSuccess(books);

    }

    private void displaySearchedBook(IGetBookView iGetBookView, BookRoot bookRoot, boolean isLoadMore) {
        iGetBookView.getBookSuceess(bookRoot, isLoadMore);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络中断", Toast.LENGTH_SHORT).show();
    }
}
