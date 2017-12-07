package com.mybareskinph.theBareskinApp.sale.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.implementations.OrderPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.views.HomeActivity;
import com.mybareskinph.theBareskinApp.home.views.OrderFragment;
import com.mybareskinph.theBareskinApp.sale.adapter.SalesHistoryAdapter;
import com.mybareskinph.theBareskinApp.sale.implementations.SalesHistoryPresenterImpl;
import com.mybareskinph.theBareskinApp.sale.pojos.SalesHistory;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.SalesHistoryView;
import com.mybareskinph.theBareskinApp.util.CalendarDate;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.DateFormats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by paulolosbanos on 9/7/17.
 */

public class SalesHistoryFragment extends BaseFragment implements SalesHistoryView {

    @BindView(R.id.rv_sales_history)
    RecyclerView salesHistoryView;

    @BindView(R.id.tv_username)
    TextView username;

    @BindView(R.id.tv_subtext)
    TextView subtext;

    SalesHistoryPresenterImpl presenter;
    SalesHistoryAdapter adapter;

    public SalesHistoryFragment() {
    }

    public static SalesHistoryFragment newInstance() {
        SalesHistoryFragment fragment = new SalesHistoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_sales_history, container, false);
        bindView(this, view);

        presenter = new SalesHistoryPresenterImpl(this, getRetrofit());
        init();
        return view;
    }

    @Override
    public void loadSalesHistory(List<SalesHistory> salesHistoryList) {
        adapter = new SalesHistoryAdapter(getContext(), salesHistoryList);
        salesHistoryView.setAdapter(adapter);
        salesHistoryView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void init() {
        ((HomeActivity) getActivity()).changeToolbarTitle("Sales History");
        username.setText(String.format(username.getText().toString(), getUserCredentials().getUsername()));
        subtext.setText(getString(R.string.label_x_report_date, "sales", DateFormats.DATE_FORMAT_EMDYYYY.format(CalendarDate.fromDate(new Date()).toJavaDate())));
    }

    @Override
    public String userId() {
        return getUserCredentials().getUid();
    }
}
