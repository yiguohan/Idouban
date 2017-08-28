package com.yiguohan.idouban.viewImpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.base.BasePresenter;
import com.yiguohan.idouban.bean.top250.Root;
import com.yiguohan.idouban.viewinterface.film.IGetTop250View;

public class MainActivity extends BaseActivity implements IGetTop250View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getTop250Success(Root root, boolean isLoadMore) {

    }

    @Override
    public void getDataFail() {

    }

    @Override
    public String setActName() {
        return null;
    }
}
