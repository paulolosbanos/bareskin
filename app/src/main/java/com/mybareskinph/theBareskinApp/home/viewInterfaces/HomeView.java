package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;

import java.util.ArrayList;
import java.util.HashMap;

public interface HomeView {

    void showFutureEarning(ArrayList<StoreItem> items);

    void hideFutureEarning();

    void showSupplyWorth(ArrayList<StoreItem> items);

    void hideSupplyWorth();

    void showInviteCode(UserCredential credential);

    void hideInviteCode();

    void goToSuppliesPage();

    void goToRegisterSalesPage();
}
