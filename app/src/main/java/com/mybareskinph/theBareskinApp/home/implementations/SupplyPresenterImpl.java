package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;

import retrofit2.Retrofit;

public class SupplyPresenterImpl implements SupplyPresenter {
    final SupplyView mView;
    final Retrofit mRetrofit;

    public SupplyPresenterImpl(SupplyView view, Retrofit retrofit) {
        this.mView = view;
        this.mRetrofit = retrofit;
    }
}
