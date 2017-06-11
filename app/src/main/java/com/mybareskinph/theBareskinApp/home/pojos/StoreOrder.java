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
    private ArrayList<Item> items = null;
    @SerializedName("status")
    @Expose
    private String status;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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