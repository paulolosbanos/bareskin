package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.implementations.SupplyPresenterImpl;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;

public class SupplyFragment extends BaseFragment implements SupplyView {

    SupplyPresenterImpl presenter;

    public SupplyFragment() {

    }

    public static SupplyFragment newInstance() {
        return new SupplyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_supply, container, false);
        bindView(this, view);
        presenter = new SupplyPresenterImpl(this, getRetrofit());
        return view;
    }
}
