package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Retrofit;

public class SupplyPresenterImpl implements SupplyPresenter {
    final SupplyView mView;
    final Retrofit mRetrofit;

    public SupplyPresenterImpl(SupplyView view, HashMap<String, Object> globalObjects, Retrofit retrofit) {
        this.mView = view;
        this.mRetrofit = retrofit;
        this.mView.loadUsername((UserCredential) globalObjects.get(Constants.USER_INFO));
        this.mView.loadSupplyDetails((ArrayList<StoreItem>) globalObjects.get(Constants.SUPPLIES));
    }
}
