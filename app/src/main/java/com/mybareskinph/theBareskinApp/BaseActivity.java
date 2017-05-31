package com.mybareskinph.theBareskinApp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Retrofit;

public class BaseActivity extends AppCompatActivity {
    Retrofit retrofit;

    public BaseActivity() {

    }

    public Retrofit getRetrofit() {
        return ((BareskinApplication) getApplication()).retrofit;
    }
}
