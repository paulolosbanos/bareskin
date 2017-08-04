package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;

public interface ProductOrderPresenter {
    void loadProducts();
    void updateOrder(OrderUnit unit);
}
