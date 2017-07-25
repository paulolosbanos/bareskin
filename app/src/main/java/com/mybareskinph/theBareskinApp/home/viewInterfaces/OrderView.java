package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;

import java.util.ArrayList;

public interface OrderView {

    void loadUsername(UserCredential credential);
    void loadOrderDetails(ArrayList<StoreOrder> orders);
}
