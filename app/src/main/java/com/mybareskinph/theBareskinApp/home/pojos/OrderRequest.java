package com.mybareskinph.theBareskinApp.home.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {
    @SerializedName("order-unit-list")
    List<OrderUnit> orderUnitList;

    @SerializedName("payment-date")
    String paymentDate;

    @SerializedName("payment-channel")
    String paymentChannel;

    String name;
    String address;
    String mobile;

    public OrderRequest() {

    }

    public List<OrderUnit> getOrderUnitList() {
        return orderUnitList;
    }

    public void setOrderUnitList(List<OrderUnit> orderUnitList) {
        this.orderUnitList = orderUnitList;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
