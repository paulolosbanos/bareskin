package com.mybareskinph.theBareskinApp.home.views;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.darkerBrown));
        }
        newOrderPager.setAdapter(new NewOrderPagerAdapter(getSupportFragmentManager(),this));
    }
}
