package com.yiguohan.idouban.viewinterface.book;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.book.BookRoot;

/**
 * Created by yiguohan.
 */

public interface IGetBookView extends IBaseView {
    void getBookSuceess(BookRoot bookRoot, boolean isLoadMore);

    void getBookFail();
}
