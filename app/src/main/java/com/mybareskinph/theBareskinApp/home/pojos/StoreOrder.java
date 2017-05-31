package com.mybareskinph.theBareskinApp.home.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreOrder {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}