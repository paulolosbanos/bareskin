package com.mybareskinph.theBareskinApp.sale.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.views.FormFragments;

/**
 * Created by paulolosbanos on 8/28/17.
 */

public class RegisterSaleSuccessFragment extends FormFragments {

    public static RegisterSaleSuccessFragment newInstance() {
        RegisterSaleSuccessFragment fragment = new RegisterSaleSuccessFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_sale_success, container, false);
        bindView(this, view);

        return view;
    }
}
