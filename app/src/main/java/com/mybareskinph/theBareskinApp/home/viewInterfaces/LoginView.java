package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import java.util.HashMap;

/**
 * Created by paulolosbanos on 7/24/17.
 */

public interface LoginView {
    void showHome();
    HashMap<String, Object> getGlobalObjects();
    void loginLoading();
}
