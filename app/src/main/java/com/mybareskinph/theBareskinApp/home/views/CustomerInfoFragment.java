package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.adapters.SupplyAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.SupplyPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;

public class CustomerInfoFragment extends BaseFragment implements SupplyView {

    public CustomerInfoFragment() {
    }

    public static CustomerInfoFragment newInstance() {
        CustomerInfoFragment fragment = new CustomerInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_info, container, false);
        bindView(this, view);

        return view;
    }
}
