package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.home.implementations.NewOrderPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.views.CustomerInfoFragment;
import com.mybareskinph.theBareskinApp.home.views.FormFragments;
import com.mybareskinph.theBareskinApp.home.views.NewOrderActivity;
import com.mybareskinph.theBareskinApp.home.views.OrderInfoFragment;
import com.mybareskinph.theBareskinApp.home.views.OrderPlacementSuccessFragment;
import com.mybareskinph.theBareskinApp.home.views.PaymentInfoFragment;
import com.mybareskinph.theBareskinApp.home.views.ProductOrdersFragment;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class NewOrderPagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGES = 5;
    SparseArray<Fragment> pages = new SparseArray<>();
    PublishSubject<Object> orderPayload;

    public NewOrderPagerAdapter(FragmentManager fm, PublishSubject<Object> orderPayload) {
        super(fm);
        this.orderPayload = orderPayload;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        pages.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        pages.remove(position);
        super.destroyItem(container, position, object);
    }

    public FormFragments getRegisteredFragment(int position) {
        return ((FormFragments) pages.get(position));
    }

    @Override
    public Fragment getItem(int position) {
        FormFragments fragment;
        switch (position) {
            case 0:
                fragment = ProductOrdersFragment.newInstance();
                ((ProductOrdersFragment) fragment)
                        .orderListWatcher()
                        .subscribe(orderUnits -> {
                            fragment.setAnswered(orderUnits.size() > 0);
                            orderPayload.onNext(orderUnits);
                        });
                break;
            case 1:
                fragment = PaymentInfoFragment.newInstance();
                ((PaymentInfoFragment) fragment)
                        .paymentInformation()
                        .subscribe(s -> {
                            fragment.setAnswered(s != null && !s.isEmpty());
                            orderPayload.onNext(s);
                        });
                break;
            case 2:
                fragment = CustomerInfoFragment.newInstance();
                ((CustomerInfoFragment) fragment)
                        .customerInfo()
                        .subscribe(ss -> {
                            fragment.setAnswered(ss != null && !ss.isEmpty());
                            orderPayload.onNext(ss);
                        });
                break;
            case 3:
                fragment = OrderInfoFragment.newInstance();
                fragment.setAnswered(true);
                break;
            case 4:
                fragment = OrderPlacementSuccessFragment.newInstance();
                fragment.setAnswered(true);
                break;
            default:
                fragment = CustomerInfoFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGES;
    }
}
