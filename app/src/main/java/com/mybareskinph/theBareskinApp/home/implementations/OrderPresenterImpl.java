package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderView;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Retrofit;

public class OrderPresenterImpl implements OrderPresenter {
    final OrderView mView;
    final Retrofit mRetrofit;

    public OrderPresenterImpl(OrderView view, HashMap<String, Object> globalObjects, Retrofit retrofit) {
        this.mView = view;
        this.mRetrofit = retrofit;
        this.mView.loadOrderDetails((ArrayList<StoreOrder>) globalObjects.get(Constants.ORDERS));
        this.mView.loadUsername((UserCredential) globalObjects.get(Constants.USER_INFO));
    }
}
