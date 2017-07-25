package com.mybareskinph.theBareskinApp.home.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.PaymentInfoView;
import com.mybareskinph.theBareskinApp.util.CalendarDate;
import com.mybareskinph.theBareskinApp.util.DatePickerFragment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by paulolosbanos on 7/9/17.
 */

public class PaymentInfoFragment extends BaseFragment implements PaymentInfoView, DatePickerFragment.DatePickerFragmentListener {

    @BindView(R.id.ms_payment_method)
    MaterialSpinner paymentMethod;

    @BindView(R.id.et_payment_date)
    TextInputEditText paymentDate;

    private static final CalendarDate DEFAULT_INITIAL_JOURNEY_DATE;

    static {
        Calendar c = Calendar.getInstance();
        DEFAULT_INITIAL_JOURNEY_DATE = new CalendarDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public PaymentInfoFragment() {

    }

    public static PaymentInfoFragment newInstance() {
        PaymentInfoFragment fragment = new PaymentInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_info, container, false);
        bindView(this, view);
        paymentMethod.setItems("BDO", "DragonPay");

        paymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                journeyDatePicker("try", new CalendarDate(2017, 6, 10));

            }
        });
        return view;
    }

    private void journeyDatePicker(String name, CalendarDate date) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentByTag(name) != null) {
            return;
        }
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(name, date != null ? date : DEFAULT_INITIAL_JOURNEY_DATE);
        datePickerFragment.setListener(this);
        datePickerFragment.show(fragmentManager);
        //ActivityUtils.hideKeyboard(this);
    }

    @Override
    public void dateSelected(String name, int year, int monthOfYear, int dayOfMonth) {
        paymentDate.setText(monthOfYear + "-" + dayOfMonth + "-" + year);
    }
}
