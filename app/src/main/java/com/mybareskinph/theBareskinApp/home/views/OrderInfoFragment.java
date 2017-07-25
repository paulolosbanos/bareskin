package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.PaymentInfoView;
import com.mybareskinph.theBareskinApp.util.CalendarDate;
import com.mybareskinph.theBareskinApp.util.DatePickerFragment;

import java.util.Calendar;

import butterknife.BindView;

/**
 * Created by paulolosbanos on 7/9/17.
 */

public class OrderInfoFragment extends BaseFragment {

    public OrderInfoFragment() {

    }

    public static OrderInfoFragment newInstance() {
        OrderInfoFragment fragment = new OrderInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_info, container, false);
        bindView(this, view);
        return view;
    }
}
