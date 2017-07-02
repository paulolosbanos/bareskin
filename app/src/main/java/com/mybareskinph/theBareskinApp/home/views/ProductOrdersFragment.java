package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;

import butterknife.BindView;

public class ProductOrdersFragment extends BaseFragment implements SupplyView {

    @BindView(R.id.et_product_name)
    AutoCompleteTextView productName;

    String[] autoCompleteString = {"#0012 - Luminous Whitening Soap"};

    public ProductOrdersFragment() {
    }

    public static ProductOrdersFragment newInstance() {
        ProductOrdersFragment fragment = new ProductOrdersFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_order, container, false);
        bindView(this, view);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.layout_auto_complete, autoCompleteString);
        productName.setAdapter(adapter);
        return view;
    }
}
