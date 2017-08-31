package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderInfoPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulolosbanos on 8/23/17.
 */

public class OrderInfoPresenterImpl implements OrderInfoPresenter {

    public OrderRequest orderRequest;
    public int shipping;

    public List<String> getPriceBreakdown() {
        List<String> breakdown = new ArrayList<>();
        int subtotal = 0;
        //shipping = 5000;
        for (OrderUnit unit : orderRequest.getOrderUnitList()) {
            int value = unit.getProduct().getProductCostUnit() * unit.getQuantity();
            breakdown.add(unit.getProduct().getProductName() + " ( × " + unit.getQuantity() + " )" + ";" + value);
            subtotal += value;
        }
        breakdown.add("line");
        breakdown.add("Subtotal;" + subtotal);
        breakdown.add("Shipping Fee;Awaiting Confirmation");
        breakdown.add("boldTotal w/o Shipping;" + (subtotal));
        return breakdown;
    }


}
