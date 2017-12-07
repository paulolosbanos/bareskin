package com.mybareskinph.theBareskinApp;

import android.app.Application;

import retrofit2.Retrofit;

public class App extends Application {

    public Retrofit retrofit;

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        String BASE_URL = String.format(
                                    getString(R.string.format_colon_delimited),
                                            BuildConfig.BARESKIN_SERVER_PROTOCOL +
                                            BuildConfig.BARESKIN_SERVER_URL,
                                            BuildConfig.BARESKIN_SERVER_PORT);

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
