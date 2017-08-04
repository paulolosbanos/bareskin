package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.Product;

/**
 * Created by paulolosbanos on 7/31/17.
 */

public interface ProductOnClickListener {
    void onProductAdded(Product product);
    void onProductRemoved(Product product);
}
