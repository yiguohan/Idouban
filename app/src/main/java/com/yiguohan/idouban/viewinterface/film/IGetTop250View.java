package com.yiguohan.idouban.viewinterface.film;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.top250.Root;

/**
 * Created by yiguohan.
 */

public interface IGetTop250View extends IBaseView {

    void getTop250Success(Root root, boolean isLoadMore);

    void getDataFail();
}
