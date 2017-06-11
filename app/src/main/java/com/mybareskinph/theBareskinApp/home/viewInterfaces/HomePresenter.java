package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;

import java.util.ArrayList;

public interface HomePresenter {
    void login();
    void onDetailsClick();
    void loadSupplies(ArrayList<StoreItem> items);
    void loadOrders(ArrayList<StoreOrder> orders);
}
