package com.yiguohan.idouban.viewImpl.book;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.BookViewPagerAdapter;
import com.yiguohan.idouban.api.BookApiUtils;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorLayout;

    private String[] mTitles;
    private BookViewPagerAdapter mBookViewPagerAdapter;

    public static BookFragment newInstance() {
        Bundle arg = new Bundle();
        BookFragment fragment = new BookFragment();
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews(){
        mTitles = BookApiUtils.Tag_Titles;

        //初始化适配器
        mBookViewPagerAdapter = new BookViewPagerAdapter(getChildFragmentManager(),mTitles);
        viewPager.setAdapter(mBookViewPagerAdapter);
        //设置最大缓存页面个数
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(mBookViewPagerAdapter);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return "动态";
    }
}
