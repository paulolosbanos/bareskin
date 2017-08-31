package com.mybareskinph.theBareskinApp.home.pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paulolosbanos on 8/28/17.
 */

public class NewOrderResponse {

    OrderRequest request;

    @SerializedName("reference-number")
    String referenceNumber;

    @SerializedName("order-date")
    String orderDate;

    String status;

    public OrderRequest getRequest() {
        return request;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }
}
