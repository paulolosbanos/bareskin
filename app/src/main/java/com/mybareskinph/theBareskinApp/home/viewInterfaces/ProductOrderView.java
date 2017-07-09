package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.Product;

import java.util.List;

public interface ProductOrderView {
    void fillRecyclerView(List<Product> productList);
}
