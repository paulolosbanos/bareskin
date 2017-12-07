package com.mybareskinph.theBareskinApp.sale.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by paulolosbanos on 9/7/17.
 */

public class SalesHistoryResponse {

    String status;

    @SerializedName("sales-history")
    List<SalesHistory> salesHistory;


    public List<SalesHistory> getSalesHistory() {
        return salesHistory;
    }

    public String getStatus() {
        return status;
    }
}
