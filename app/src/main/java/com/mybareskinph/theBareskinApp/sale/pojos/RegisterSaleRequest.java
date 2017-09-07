package com.mybareskinph.theBareskinApp.sale.pojos;

import com.google.gson.annotations.SerializedName;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by paulolosbanos on 9/1/17.
 */

public class RegisterSaleRequest implements Serializable {

    @SerializedName("buyer-name")
    String buyerName;

    @SerializedName("buyer-number")
    String buyerNumber;

    List<OrderUnit> purchases;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public List<OrderUnit> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<OrderUnit> purchases) {
        this.purchases = purchases;
    }
}
