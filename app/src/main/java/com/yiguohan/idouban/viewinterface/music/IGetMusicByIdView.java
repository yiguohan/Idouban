package com.yiguohan.idouban.viewinterface.music;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.music.Musics;

/**
 * Created by yiguohan.
 */

public interface IGetMusicByIdView extends IBaseView {

    void getMusicSuccess(Musics musics);

}
