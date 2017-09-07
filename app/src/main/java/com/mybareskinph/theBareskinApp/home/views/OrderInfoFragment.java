package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.adapters.PriceBreakdownAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.OrderInfoPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderInfoView;

import butterknife.BindView;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 7/9/17.
 */

public class OrderInfoFragment extends FormFragments implements OrderInfoView {

    @BindView(R.id.et_fullname)
    TextView fullname;

    @BindView(R.id.et_address)
    TextView address;

    @BindView(R.id.et_number)
    TextView number;

    @BindView(R.id.et_payment_date)
    TextView paymentDate;

    @BindView(R.id.et_payment_method)
    TextView paymentMethod;

    @BindView(R.id.rv_price_breakdown)
    RecyclerView priceBreakdown;

    private static final String ORDER = "order";
    PublishSubject<OrderRequest> requestObservable = PublishSubject.create();

    OrderInfoPresenterImpl presenter;

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
        presenter = new OrderInfoPresenterImpl();

        requestObservable.subscribe(orderRequest -> {
            presenter.orderRequest = orderRequest;
            fullname.setText(orderRequest.getName());
            address.setText(orderRequest.getAddress());
            number.setText(orderRequest.getMobile());
            paymentMethod.setText(orderRequest.getPaymentChannel());
            paymentDate.setText(orderRequest.getPaymentDate());

            PriceBreakdownAdapter priceBreakdownAdapter = new PriceBreakdownAdapter(getContext(), presenter.getPriceBreakdown());
            priceBreakdown.setAdapter(priceBreakdownAdapter);
            priceBreakdown.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        return view;
    }

    public PublishSubject<OrderRequest> requestObservable() {
        return requestObservable;
    }
}
