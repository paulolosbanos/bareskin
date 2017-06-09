package com.mybareskinph.theBareskinApp.home.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreInventory {

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

}