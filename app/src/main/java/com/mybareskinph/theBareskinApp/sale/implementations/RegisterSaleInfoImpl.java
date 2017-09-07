package com.mybareskinph.theBareskinApp.sale.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleRequest;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.RegisterSalePresenter;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.RegisterSaleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulolosbanos on 9/3/17.
 */

public class RegisterSaleInfoImpl implements RegisterSalePresenter {
    RegisterSaleView mView;
    public RegisterSaleRequest request;

    public List<String> getPriceBreakdown() {
        List<String> breakdown = new ArrayList<>();
        int subtotal = 0;
        //shipping = 5000;
        for (OrderUnit unit : request.getPurchases()) {
            int value = unit.getProduct().getProductSrpUnit() * unit.getQuantity();
            breakdown.add(unit.getProduct().getProductName() + " ( × " + unit.getQuantity() + " )" + ";" + value);
            subtotal += value;
        }
        breakdown.add("line");
        breakdown.add("boldTotal Sale;" + (subtotal));
        return breakdown;
    }
}
