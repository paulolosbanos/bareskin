package com.mybareskinph.theBareskinApp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mybareskinph.theBareskinApp.App;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Retrofit;

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

    public App getmAppInstance() {
        return mAppInstance;
    }

}
