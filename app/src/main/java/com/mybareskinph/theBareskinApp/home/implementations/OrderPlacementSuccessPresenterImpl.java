package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderPlacementSuccessPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulolosbanos on 8/29/17.
 */

public class OrderPlacementSuccessPresenterImpl implements OrderPlacementSuccessPresenter {

    public NewOrderResponse response;

    public List<String> getPriceBreakdown() {
        List<String> breakdown = new ArrayList<>();
        int subtotal = 0;
        //shipping = 5000;
        for (OrderUnit unit : response.getRequest().getOrderUnitList()) {
            int value = unit.getProduct().getProductCostUnit() * unit.getQuantity();
       //     breakdown.add(unit.getProduct().getProductName() + " ( × " + unit.getQuantity() + " )" + ";" + value);
            subtotal += value;
        }
        //breakdown.add("line");
        breakdown.add("Subtotal;" + subtotal);
        breakdown.add("Shipping Fee;Awaiting Confirmation");
        breakdown.add("boldTotal w/o Shipping;" + (subtotal));
        return breakdown;
    }
}
