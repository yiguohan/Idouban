package com.yiguohan.idouban.viewinterface.film;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.filmdetail.FilmDetail;

/**
 * Created by yiguohan.
 */

public interface IGetFilmDetailView extends IBaseView {
    void getFilmDetailSuccess(FilmDetail filmDetail);

    void getFilmDetailFail();
}
