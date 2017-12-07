package com.mybareskinph.theBareskinApp.sale.viewinterfaces;

import com.mybareskinph.theBareskinApp.sale.pojos.SalesHistory;

import java.util.List;

/**
 * Created by paulolosbanos on 9/7/17.
 */

public interface SalesHistoryView {
    void loadSalesHistory(List<SalesHistory> salesHistoryList);

    String userId();
}
