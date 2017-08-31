package com.mybareskinph.theBareskinApp.home.viewInterfaces;

import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;

/**
 * Created by paulolosbanos on 8/12/17.
 */

public interface NewOrderView {
    void enableBackButton(boolean condition);
    void enableNextButton(boolean condition);
    void enableLoading(boolean condition);
    void orderPlacementSuccess(NewOrderResponse response);
    void setFinalButton();
}
