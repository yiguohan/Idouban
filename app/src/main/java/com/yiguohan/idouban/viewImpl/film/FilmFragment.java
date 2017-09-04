package com.yiguohan.idouban.viewImpl.film;


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
import com.yiguohan.idouban.adapter.MyViewPagerAdapter;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment implements ViewPager.OnPageChangeListener {


    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    //标题
    private String[] mTitles;
    //fragment集合
    private List<Fragment> mFragments;
    private FilmLiveFragment filmLiveFragment;
    private FilmTop250Fragment filmTop250Fragment;
    private MyViewPagerAdapter mViewPagerAdatper;


    public static FilmFragment newInstance() {
        Bundle arg = new Bundle();
        FilmFragment fragment = new FilmFragment();
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mTitles = getResources().getStringArray(R.array.tab_film);
        //初始化填充到ViewPager中的fragment集合
        mFragments = new ArrayList<>();
        filmLiveFragment = FilmLiveFragment.newInstance();
        filmTop250Fragment = FilmTop250Fragment.newInstance();
        mFragments.add(filmLiveFragment);
        mFragments.add(filmTop250Fragment);

        mViewPagerAdatper = new MyViewPagerAdapter(getChildFragmentManager(), mTitles, mFragments);
        viewPager.setAdapter(mViewPagerAdatper);

        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6), ThemeUtils.getThemeColor());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(mViewPagerAdatper);


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
        return true;
    }

    @Override
    protected String setFragmentName() {
        return "首页-Fragment";
    }
}
