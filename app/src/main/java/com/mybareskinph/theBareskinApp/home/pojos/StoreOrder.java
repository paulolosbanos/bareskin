package com.mybareskinph.theBareskinApp.home.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreOrder implements Parcelable, Serializable {

    public static final Parcelable.Creator<StoreOrder> CREATOR = new Parcelable.Creator<StoreOrder>() {
        public StoreOrder createFromParcel(Parcel source) {
            return new StoreOrder(source);
        }

        public StoreOrder[] newArray(int size) {
            return new StoreOrder[size];
        }
    };

    @SerializedName("items")
    @Expose
    private ArrayList<StoreItem> items = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order-start-date")
    @Expose
    private String orderStartDate;
    @SerializedName("order-id")
    @Expose
    private String orderId;


    public ArrayList<StoreItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<StoreItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(String orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public StoreOrder() {

    }

    public StoreOrder(Parcel in) {
        this.items = in.readArrayList(Item.class.getClassLoader());
        this.status = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
        dest.writeString(status);
    }
}