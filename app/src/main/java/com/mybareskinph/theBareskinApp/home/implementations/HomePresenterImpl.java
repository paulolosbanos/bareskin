package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomePresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomeView;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter {

    private HomeView mView;
    private Retrofit mRetrofit;

    public HomePresenterImpl(HomeView view, Retrofit retrofit) {
        this.mView = view;
        this.mRetrofit = retrofit;
    }

    @Override
    public void login() {
        MainService svc = mRetrofit.create(MainService.class);
        mView.hideSupplyWorth();
        mView.hideFutureEarning();
        mView.hideInviteCode();
        svc.login()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            LoggerUtil.log(((HttpException) e).code());
                        } else if (e instanceof IOException) {
                            LoggerUtil.log(e);
                        }
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        mView.showFutureEarning(loginResponse);
                        mView.showInviteCode(loginResponse);
                        mView.showSupplyWorth(loginResponse);
                        loadSupplies(loginResponse.getStoreInventory());
                        loadOrders(loginResponse.getStoreOrders());
                    }
                });
    }

    @Override
    public void onDetailsClick() {
        mView.goToSuppliesPage();
    }

    @Override
    public void loadSupplies(ArrayList<StoreItem> items) {
        mView.getGlobalObjects().put(Constants.SUPPLIES, items);
    }

    @Override
    public void loadOrders(ArrayList<StoreOrder> orders) {
        mView.getGlobalObjects().put(Constants.ORDERS, orders);
    }
}
