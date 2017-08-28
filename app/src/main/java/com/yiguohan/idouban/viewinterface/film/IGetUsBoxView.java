package com.yiguohan.idouban.viewinterface.film;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.filmusbox.FilmUsBox;

/**
 * Created by yiguohan.
 */

public interface IGetUsBoxView extends IBaseView {
    void getUsBoxSuccess(FilmUsBox filmUsBox);

    void getDataFail();
}
