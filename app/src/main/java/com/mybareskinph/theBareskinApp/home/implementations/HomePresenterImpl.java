package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomePresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomeView;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
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
        Observable.just(null)
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avoid -> mView.goToSuppliesPage());

    }

    @Override
    public void onRegisterSalesClick() {
        mView.goToRegisterSalesPage();
    }
}
