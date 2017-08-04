package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;

import java.util.ArrayList;

public interface SupplyView {
    void loadUsername(UserCredential credential);
    void loadSupplyDetails(ArrayList<StoreItem> orders);
}
