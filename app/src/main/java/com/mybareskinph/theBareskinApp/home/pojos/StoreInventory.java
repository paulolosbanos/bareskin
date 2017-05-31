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
    private Integer itemCostUnit;
    @SerializedName("item-srp-unit")
    @Expose
    private Integer itemSrpUnit;
    @SerializedName("item-qty")
    @Expose
    private Integer itemQty;

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

    public Integer getItemCostUnit() {
        return itemCostUnit;
    }

    public void setItemCostUnit(Integer itemCostUnit) {
        this.itemCostUnit = itemCostUnit;
    }

    public Integer getItemSrpUnit() {
        return itemSrpUnit;
    }

    public void setItemSrpUnit(Integer itemSrpUnit) {
        this.itemSrpUnit = itemSrpUnit;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

}