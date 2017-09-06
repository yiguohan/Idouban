package com.yiguohan.idouban.viewImpl.music;


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
import com.yiguohan.idouban.adapter.MusicViewPagerAdapter;
import com.yiguohan.idouban.api.MusicApiUtils;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends BaseFragment implements ViewPager.OnPageChangeListener {


    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorLayout;

    private String[] mTitles;
    private MusicViewPagerAdapter musicViewPagerAdapter;

    public static MusicFragment newInstance() {
        Bundle arg = new Bundle();
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
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
        return null;
    }

    private void initView(){
        mTitles = MusicApiUtils.Music_Titles;
        musicViewPagerAdapter = new MusicViewPagerAdapter(getChildFragmentManager(),mTitles);
        viewPager.setAdapter(musicViewPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(musicViewPagerAdapter);
    }
}
