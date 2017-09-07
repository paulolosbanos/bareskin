package com.mybareskinph.theBareskinApp.sale.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.home.views.FormFragments;
import com.mybareskinph.theBareskinApp.home.views.ProductOrdersFragment;
import com.mybareskinph.theBareskinApp.sale.views.RegisterSaleFragment;
import com.mybareskinph.theBareskinApp.sale.views.RegisterSaleInfoFragment;
import com.mybareskinph.theBareskinApp.sale.views.RegisterSaleSuccessFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 8/31/17.
 */

public class RegisterSaleAdapter extends FragmentStatePagerAdapter {

    private static final int PAGES = 4;
    SparseArray<Fragment> pages = new SparseArray<>();
    PublishSubject<Object> requestPayload;

    public RegisterSaleAdapter(FragmentManager fm, PublishSubject<Object> requestPayload) {
        super(fm);
        this.requestPayload = requestPayload;
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
                fragment = RegisterSaleFragment.newInstance();
                ((RegisterSaleFragment) fragment).getBuyerInfoObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            fragment.setAnswered(s != null);
                            requestPayload.onNext(s);
                        });
                break;
            case 1:
                fragment = ProductOrdersFragment.newInstance(ProductOrdersFragment.REGISTER_SALE_MODE);
                ((ProductOrdersFragment) fragment)
                        .orderListWatcher()
                        .subscribe(orderUnits -> {
                            fragment.setAnswered(orderUnits.size() > 0);
                            requestPayload.onNext(orderUnits);
                        });
                break;
            case 2:
                fragment = RegisterSaleInfoFragment.newInstance();
                fragment.setAnswered(true);
                break;
            case 3:
                fragment = RegisterSaleSuccessFragment.newInstance();
                fragment.setAnswered(true);
                break;
            default:
                fragment = RegisterSaleFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGES;
    }
}
