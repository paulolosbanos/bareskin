package com.mybareskinph.theBareskinApp.sale.pojos;

import com.google.gson.annotations.SerializedName;
import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by paulolosbanos on 9/7/17.
 */

public class SalesHistory implements Serializable {

    @SerializedName("reference-number")
    String referenceNumber;

    @SerializedName("buyer-name")
    String buyerName;

    @SerializedName("buyer-number")
    String buyerNumber;

    @SerializedName("buyer-purchases")
    List<OrderUnit> buyerPurchases;

    @SerializedName("payment-channel")
    String paymentChannel;

    @SerializedName("payment-date")
    String paymentDate;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public List<OrderUnit> getBuyerPurchases() {
        return buyerPurchases;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
}
