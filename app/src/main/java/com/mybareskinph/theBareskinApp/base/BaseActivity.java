package com.mybareskinph.theBareskinApp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mybareskinph.theBareskinApp.App;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class BaseActivity extends RxAppCompatActivity {

    @Inject
    Retrofit mRetrofit;

    @Inject
    HashMap<String, Object> globalObjects;

    App mAppInstance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppInstance = ((App) getApplication());
        mAppInstance.getNetComponent().inject(this);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public HashMap<String, Object> getGlobalObjects() {
        return globalObjects;
    }

    public UserCredential getUserCredentials() {
        return (UserCredential) getGlobalObjects().get(Constants.USER_INFO);
    }

    public App getmAppInstance() {
        return mAppInstance;
    }

    @NonNull
    public final <T> Observable.Transformer<T, T> bind() {
        return observable -> observable
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
