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
import com.mybareskinph.theBareskinApp.home.adapters.PriceBreakdownAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.OrderPlacementSuccessPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import butterknife.BindView;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 8/28/17.
 */

public class OrderPlacementSuccessFragment extends FormFragments {

    @BindView(R.id.rv_price_breakdown)
    RecyclerView priceBreakdown;

    public static OrderPlacementSuccessFragment newInstance() {
        OrderPlacementSuccessFragment fragment = new OrderPlacementSuccessFragment();
        return fragment;
    }

    PublishSubject<NewOrderResponse> responseSubject = PublishSubject.create();
    OrderPlacementSuccessPresenterImpl presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_placement_success, container, false);
        bindView(this, view);
        presenter = new OrderPlacementSuccessPresenterImpl();

        responseSubject
                .asObservable()
                .subscribe(response -> {
                    presenter.response = response;
                    PriceBreakdownAdapter priceBreakdownAdapter = new PriceBreakdownAdapter(getContext(), presenter.getPriceBreakdown());
                    priceBreakdown.setAdapter(priceBreakdownAdapter);
                    priceBreakdown.setLayoutManager(new LinearLayoutManager(getContext()));
                });

        return view;
    }
}
