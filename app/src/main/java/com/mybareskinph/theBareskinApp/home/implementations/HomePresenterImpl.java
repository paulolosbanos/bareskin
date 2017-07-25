package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.LoginRequest;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomePresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomeView;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter {

    private HomeView mView;

    public HomePresenterImpl(HomeView view, HashMap<String, Object> globalObjects) {
        this.mView = view;
        this.mView.showFutureEarning((ArrayList<StoreItem>) globalObjects.get(Constants.SUPPLIES));
        this.mView.showSupplyWorth((ArrayList<StoreItem>) globalObjects.get(Constants.SUPPLIES));
        this.mView.showInviteCode((UserCredential) globalObjects.get(Constants.USER_INFO));
    }

    @Override
    public void onDetailsClick() {
        mView.goToSuppliesPage();
    }
}
