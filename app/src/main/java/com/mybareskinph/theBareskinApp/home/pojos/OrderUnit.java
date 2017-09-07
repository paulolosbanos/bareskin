package com.mybareskinph.theBareskinApp.home.pojos;

/**
 * Created by paulolosbanos on 8/1/17.
 */

public class OrderUnit {
    Product product = new Product();
    Integer quantity = 0;

    public OrderUnit() {

    }

    public OrderUnit(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void addQuantity() {
        quantity++;
    }
}
