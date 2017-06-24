package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mybareskinph.theBareskinApp.home.views.CustomerInfoFragment;

public class NewOrderPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private static final int PAGES = 3;

    public NewOrderPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CustomerInfoFragment();
            case 1:
                return new CustomerInfoFragment();
            case 2:
                return new CustomerInfoFragment();
            default:
                return new CustomerInfoFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGES;
    }
}
