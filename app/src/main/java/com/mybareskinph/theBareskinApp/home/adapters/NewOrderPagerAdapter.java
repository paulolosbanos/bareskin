package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mybareskinph.theBareskinApp.home.views.CustomerInfoFragment;
import com.mybareskinph.theBareskinApp.home.views.OrderInfoFragment;
import com.mybareskinph.theBareskinApp.home.views.PaymentInfoFragment;
import com.mybareskinph.theBareskinApp.home.views.ProductOrdersFragment;

public class NewOrderPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private static final int PAGES = 4;

    public NewOrderPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProductOrdersFragment();
            case 1:
                return new PaymentInfoFragment();
            case 2:
                return new CustomerInfoFragment();
            case 3:
                return new OrderInfoFragment();
            default:
                return new CustomerInfoFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGES;
    }
}
