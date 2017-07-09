package com.mybareskinph.theBareskinApp.home.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by paulolosbanos on 7/6/17.
 */

public class Product {
    @SerializedName("product-id")
    @Expose
    private String productId;
    @SerializedName("product-name")
    @Expose
    private String productName;
    @SerializedName("product-cost-unit")
    @Expose
    private Integer productCostUnit;
    @SerializedName("product-srp-unit")
    @Expose
    private Integer productSrpUnit;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductCostUnit() {
        return productCostUnit;
    }

    public Integer getProductSrpUnit() {
        return productSrpUnit;
    }
}
