package com.yiguohan.idouban.viewinterface.book;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.book.Books;

/**
 * Created by yiguohan.
 */

public interface IGetBookDetailView extends IBaseView {
    void getBookSuccess(Books books);

    void getBookFail();
}
