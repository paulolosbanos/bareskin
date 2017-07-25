package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.LoginRequest;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.LoginPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.LoginView;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by paulolosbanos on 7/24/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mView;
    private Retrofit mRetrofit;
    private LoginRequest request;

    public LoginPresenterImpl(LoginView mView, Retrofit mRetrofit) {
        this.mView = mView;
        this.mRetrofit = mRetrofit;
        this.request = new LoginRequest();
    }

    @Override
    public void login() {
        MainService svc = mRetrofit.create(MainService.class);
        svc.login(request)
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
                        loadOrders(loginResponse.getStoreOrders());
                        loadSupplies(loginResponse.getStoreInventory());
                        loadPersonalInfo(loginResponse.getUserCredential());
                        mView.showHome();
                    }

                });
    }

    public void setId(String id) {
        this.request.setId(id);
    }

    public void setPassword(String password) {
        this.request.setPassword(password);
    }

    @Override
    public void loadSupplies(ArrayList<StoreItem> items) {
        mView.getGlobalObjects().put(Constants.SUPPLIES, items);
    }

    @Override
    public void loadOrders(ArrayList<StoreOrder> orders) {
        mView.getGlobalObjects().put(Constants.ORDERS, orders);
    }

    @Override
    public void loadPersonalInfo(UserCredential cred) {
        mView.getGlobalObjects().put(Constants.USER_INFO, cred);
    }
}
