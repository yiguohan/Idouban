package com.yiguohan.idouban.viewImpl.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;

public class EntryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
    }

    @Override
    public String setActName() {
        return null;
    }
}
