package com.mybareskinph.theBareskinApp;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {
    Application mApplication;
    HashMap<String, Object> globalObjects  = new HashMap<>();

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    HashMap<String, Object> provideGlobalObjects() {
        return globalObjects;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
