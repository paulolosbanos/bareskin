package com.mybareskinph.theBareskinApp.util;

import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;

import java.util.List;

public class StoreComputationUtil {

    public static Long computeEarningTrajectory(List<StoreItem> items) {
        Long total = 0L;
        for(StoreItem item : items) {
            long unitProfit = computeUnitProfit(item);
            long totalProfit = unitProfit * item.getItemQty();
            total += totalProfit;
        }
        return total;
    }

    private static Long computeUnitProfit(StoreItem item) {
        return item.getItemSrpUnit() - item.getItemCostUnit();
    }


}
