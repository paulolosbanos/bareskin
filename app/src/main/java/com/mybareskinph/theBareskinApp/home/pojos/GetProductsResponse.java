package com.mybareskinph.theBareskinApp.home.pojos;

import java.util.ArrayList;

/**
 * Created by paulolosbanos on 7/6/17.
 */

public class GetProductsResponse {
    String status;
    ArrayList<Product> products;
    ArrayList<String> tags;

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
