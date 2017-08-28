package com.yiguohan.idouban.viewinterface.music;

import com.yiguohan.idouban.base.IBaseView;
import com.yiguohan.idouban.bean.music.MusicRoot;

/**
 * Created by yiguohan.
 */

public interface IGetMusicByTagView extends IBaseView {

    void getMusicByTagSuceess(MusicRoot musicRoot, boolean isLoadMore);
}
