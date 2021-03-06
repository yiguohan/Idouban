package com.yiguohan.idouban.api;

import com.yiguohan.idouban.bean.book.BookRoot;
import com.yiguohan.idouban.bean.book.Books;
import com.yiguohan.idouban.bean.filmdetail.FilmDetail;
import com.yiguohan.idouban.bean.filmlive.FilmLive;
import com.yiguohan.idouban.bean.filmusbox.FilmUsBox;
import com.yiguohan.idouban.bean.music.MusicRoot;
import com.yiguohan.idouban.bean.music.Musics;
import com.yiguohan.idouban.bean.top250.Root;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yiguohan.
 */

public interface DoubanApi {

    /**
     * 获取热映中的电影
     *
     * @return
     */
    @GET("v2/movie/in_theaters")
    Observable<FilmLive> getLiveFilm();

    /**
     * 北美榜单
     *
     * @return
     */
    @GET("v2/movie/us_box")
    Observable<FilmUsBox> getUsBox();

    /**
     * 获取Top250
     *
     * @param start
     * @param count
     * @return
     */
    @GET("v2/movie/top250")
    Observable<Root> getTop250(@Query("start") int start, @Query("count") int count);

    /**
     * 根据id获取电影详情
     *
     * @param id
     * @return
     */
    @GET("v2/movie/subject/{id]")
    Observable<FilmDetail> getFilmDetail(@Path("id") String id);

    /**
     * 根据tag搜索图书
     *
     * @param tag
     * @return
     */
    @GET("v2/book/search")
    Observable<BookRoot> searchBookByTag(@Query("tag") String tag);

    @GET("v2/book/{id}")
    Observable<Books> getBookDetail(@Path("id") String id);

    /**
     * 根据tag搜索
     *
     * @param tag
     * @return
     */
    @GET("v2/music/search")
    Observable<MusicRoot> searchMusicByTag(@Query("tag") String tag);

    @GET("v2/music/{id}")
    Observable<Musics> getMusicDetail(@Path("id") String id);

}
