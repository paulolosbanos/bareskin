package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;

import rx.subjects.PublishSubject;

public interface ProductOrderPresenter {
    void loadProducts();
    void updateOrder(OrderUnit unit, PublishSubject<Integer> subject);
}
