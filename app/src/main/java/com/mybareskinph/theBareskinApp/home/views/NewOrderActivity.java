package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseActivity;

import butterknife.ButterKnife;

public class NewOrderActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        ButterKnife.bind(this);
    }
}
