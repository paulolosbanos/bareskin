package com.mybareskinph.theBareskinApp;

import android.app.Application;

import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
