package com.yiguohan.idouban.viewImpl.other;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        applyKitKatTranslucency();
        toolbar.setTitle("关于");
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });

    }

    @Override
    public String setActName() {
        return null;
    }
}
