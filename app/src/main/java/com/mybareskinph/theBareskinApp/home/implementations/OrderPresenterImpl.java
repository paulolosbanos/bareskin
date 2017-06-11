package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderView;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;

import retrofit2.Retrofit;

public class OrderPresenterImpl implements OrderPresenter {
    final OrderView mView;
    final Retrofit mRetrofit;

    public OrderPresenterImpl(OrderView view, Retrofit retrofit) {
        this.mView = view;
        this.mRetrofit = retrofit;
    }
}
