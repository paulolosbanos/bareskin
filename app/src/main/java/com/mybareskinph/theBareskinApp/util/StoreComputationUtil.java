package com.mybareskinph.theBareskinApp.util;

import com.mybareskinph.theBareskinApp.home.pojos.StoreInventory;

import java.util.List;

public class StoreComputationUtil {

    public static Long computeEarningTrajectory(List<StoreInventory> items) {
        Long total = 0L;
        for(StoreInventory item : items) {
            long unitProfit = computeUnitProfit(item);
            long totalProfit = unitProfit * item.getItemQty();
            total += totalProfit;
        }
        return total;
    }

    private static Long computeUnitProfit(StoreInventory item) {
        return item.getItemSrpUnit() - item.getItemCostUnit();
    }


}
