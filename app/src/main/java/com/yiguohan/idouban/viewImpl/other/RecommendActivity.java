package com.yiguohan.idouban.viewImpl.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.iv_topic)
    ImageView ivTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ButterKnife.bind(this);
        applyKitKatTranslucency(getResources().getColor(R.color.black));
        initView();
    }

    private void initView() {
        toolBar.setBackgroundColor(getResources().getColor(R.color.black));
        toolBar.setNavigationIcon(R.drawable.back);
        toolBar.setTitle("推荐");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
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
