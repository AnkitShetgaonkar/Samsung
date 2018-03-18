package com.droid.ankit.samsung;

/**
 * Created by Ankit on 3/17/2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<PagerFragment> mUserFragments;
    private CharSequence[] tabTitles = {"Now Showing","Upcoming"};

    public ScreenSlidePagerAdapter(List<PagerFragment> userFragments, FragmentManager fm) {
        super(fm);
        this.mUserFragments = userFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mUserFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return mUserFragments.size();
    }
}
