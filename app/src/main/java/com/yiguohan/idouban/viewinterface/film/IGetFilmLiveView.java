package com.yiguohan.idouban.viewinterface.film;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.filmlive.FilmLive;

/**
 * Created by yiguohan.
 */

public interface IGetFilmLiveView extends IBaseView {
    void getFilmLiveSuccess(FilmLive filmLive);

    void getDataFail();
}
