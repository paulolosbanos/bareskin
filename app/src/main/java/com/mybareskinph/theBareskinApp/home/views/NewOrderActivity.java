package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseActivity;
import com.mybareskinph.theBareskinApp.home.adapters.NewOrderPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewOrderActivity extends BaseActivity {

    @BindView(R.id.vp_new_order)
    ViewPager newOrderPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        ButterKnife.bind(this);

        newOrderPager.setAdapter(new NewOrderPagerAdapter(getSupportFragmentManager(),this));
    }
}
