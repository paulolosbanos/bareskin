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
import com.mybareskinph.theBareskinApp.home.adapters.PriceBreakdownAdapter;
import com.mybareskinph.theBareskinApp.home.views.FormFragments;
import com.mybareskinph.theBareskinApp.sale.implementations.RegisterSaleInfoImpl;
import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleRequest;

import butterknife.BindView;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 9/1/17.
 */

public class RegisterSaleInfoFragment extends FormFragments {

    @BindView(R.id.tv_fullname)
    TextView fullname;

    @BindView(R.id.tv_number)
    TextView number;

    @BindView(R.id.rv_price_breakdown)
    RecyclerView priceBreakdown;

    public static RegisterSaleInfoFragment newInstance() {
        return new RegisterSaleInfoFragment();
    }
    PublishSubject<RegisterSaleRequest> requestObservable = PublishSubject.create();

    RegisterSaleInfoImpl presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_sale_info, container, false);
        bindView(this, view);
        presenter = new RegisterSaleInfoImpl();

        requestObservable.subscribe(registerSaleRequest -> {
            presenter.request = registerSaleRequest;
            if(registerSaleRequest.getBuyerName() != null) {
                fullname.setText(registerSaleRequest.getBuyerName());
            }
            if(registerSaleRequest.getBuyerNumber() != null) {
                number.setText(registerSaleRequest.getBuyerNumber());
            }
            PriceBreakdownAdapter priceBreakdownAdapter = new PriceBreakdownAdapter(getContext(), presenter.getPriceBreakdown());
            priceBreakdown.setAdapter(priceBreakdownAdapter);
            priceBreakdown.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        return view;
    }

    public PublishSubject<RegisterSaleRequest> requestObservable() {
        return requestObservable;
    }
}
