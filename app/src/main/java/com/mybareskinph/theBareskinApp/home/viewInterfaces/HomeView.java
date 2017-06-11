package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;

import java.util.HashMap;

public interface HomeView {

    void showFutureEarning(LoginResponse loginResponse);

    void hideFutureEarning();

    void showSupplyWorth(LoginResponse loginResponse);

    void hideSupplyWorth();

    void showInviteCode(LoginResponse loginResponse);

    void hideInviteCode();

    void goToSuppliesPage();

    HashMap<String, Object> getGlobalObjects();
}
