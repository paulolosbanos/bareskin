package com.mybareskinph.theBareskinApp;

import android.app.Application;

import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public Retrofit retrofit;
    public static final String BASE_URL = "http://172.20.10.5:8087";
    //public static final String BASE_URL = "http://192.168.1.14:8087";
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
