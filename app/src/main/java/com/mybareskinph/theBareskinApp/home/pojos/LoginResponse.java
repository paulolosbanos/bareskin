package com.mybareskinph.theBareskinApp.home.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user-credential")
    @Expose
    private UserCredential userCredential;
    @SerializedName("store-points")
    @Expose
    private Integer storePoints;
    @SerializedName("store-value")
    @Expose
    private Long storeValue;
    @SerializedName("store-inventory")
    @Expose
    private ArrayList<StoreItem> storeInventory = null;
    @SerializedName("store-orders")
    @Expose
    private ArrayList<StoreOrder> storeOrders = null;

    private List<LocationMembers> distributors;

    private List<LocationMembers> resellers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public Integer getStorePoints() {
        return storePoints;
    }

    public void setStorePoints(Integer storePoints) {
        this.storePoints = storePoints;
    }

    public Long getStoreValue() {
        return storeValue;
    }

    public void setStoreValue(Long storeValue) {
        this.storeValue = storeValue;
    }

    public ArrayList<StoreItem> getStoreInventory() {
        return storeInventory;
    }

    public void setStoreInventory(ArrayList<StoreItem> storeInventory) {
        this.storeInventory = storeInventory;
    }

    public ArrayList<StoreOrder> getStoreOrders() {
        return storeOrders;
    }

    public void setStoreOrders(ArrayList<StoreOrder> storeOrders) {
        this.storeOrders = storeOrders;
    }

    public List<LocationMembers> getDistributors() {
        return distributors;
    }

    public List<LocationMembers> getResellers() {
        return resellers;
    }
}
