package com.markusschaden.homeautomation.airconditioner;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.markusschaden.homeautomation.airconditioner.domain.Day;

/**
 * Created by Markus on 30.01.2016.
 */
public class WeekPager extends FragmentPagerAdapter {
    final int PAGE_COUNT = 7;
    private String tabTitles[] = new String[] { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };
    private Context context;

    public WeekPager(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return DayCoolingEntryFragment.newInstance(Day.values()[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
