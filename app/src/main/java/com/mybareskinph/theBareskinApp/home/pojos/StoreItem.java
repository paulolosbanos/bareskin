package com.mybareskinph.theBareskinApp.home.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreItem implements Parcelable, Serializable{

    public static final Parcelable.Creator<StoreItem> CREATOR = new Parcelable.Creator<StoreItem>() {
        public StoreItem createFromParcel(Parcel source) {
            return new StoreItem(source);
        }

        public StoreItem[] newArray(int size) {
            return new StoreItem[size];
        }
    };

    @SerializedName("item-id")
    @Expose
    private String itemId;
    @SerializedName("item-name")
    @Expose
    private String itemName;
    @SerializedName("item-cost-unit")
    @Expose
    private Long itemCostUnit;
    @SerializedName("item-srp-unit")
    @Expose
    private Long itemSrpUnit;
    @SerializedName("item-qty")
    @Expose
    private Long itemQty;

    public StoreItem() {

    }

    public StoreItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.itemCostUnit = in.readLong();
        this.itemSrpUnit = in.readLong();
        this.itemQty = in.readLong();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemCostUnit() {
        return itemCostUnit;
    }

    public void setItemCostUnit(Long itemCostUnit) {
        this.itemCostUnit = itemCostUnit;
    }

    public Long getItemSrpUnit() {
        return itemSrpUnit;
    }

    public void setItemSrpUnit(Long itemSrpUnit) {
        this.itemSrpUnit = itemSrpUnit;
    }

    public Long getItemQty() {
        return itemQty;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemId);
        dest.writeString(itemName);
        dest.writeLong(itemCostUnit);
        dest.writeLong(itemSrpUnit);
        dest.writeLong(itemQty);
    }
}