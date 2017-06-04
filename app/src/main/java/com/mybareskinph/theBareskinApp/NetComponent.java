package com.mybareskinph.theBareskinApp;

import com.mybareskinph.theBareskinApp.base.BaseActivity;
import com.mybareskinph.theBareskinApp.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);
}
