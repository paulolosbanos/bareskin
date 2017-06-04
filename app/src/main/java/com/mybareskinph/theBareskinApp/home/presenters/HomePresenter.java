package com.mybareskinph.theBareskinApp.home.presenters;

import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomeView;

public class HomePresenter {
    final HomeView viewPresenter;

    public HomePresenter(HomeView viewPresenter) {
        this.viewPresenter = viewPresenter;
    }

    public void login() {
        viewPresenter.onLogin();
    }
}
