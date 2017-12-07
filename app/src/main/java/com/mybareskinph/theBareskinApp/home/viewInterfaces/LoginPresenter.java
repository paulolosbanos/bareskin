package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.LocationMembers;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulolosbanos on 7/24/17.
 */

public interface LoginPresenter {
    void login();
    void loadSupplies(ArrayList<StoreItem> items);
    void loadOrders(ArrayList<StoreOrder> orders);
    void loadPersonalInfo(UserCredential cred);
    void loadDistributors(List<LocationMembers> distributors);
    void loadResellers(List<LocationMembers> resellers);
}
