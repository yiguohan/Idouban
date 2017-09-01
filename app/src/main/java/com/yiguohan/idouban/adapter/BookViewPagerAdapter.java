package com.yiguohan.idouban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yiguohan.idouban.viewImpl.book.BookReadingFragment;

/**
 * Created by yiguohan.
 */

public class BookViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;

    public BookViewPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return BookReadingFragment.newInstance(position, mTitles[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
