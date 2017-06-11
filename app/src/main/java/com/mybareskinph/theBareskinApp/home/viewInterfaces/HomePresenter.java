package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;

import java.util.ArrayList;

public interface HomePresenter {
    void login();
    void onDetailsClick();
    void loadSupplies(ArrayList<StoreItem> items);
}
