package com.mybareskinph.theBareskinApp.sale.viewinterfaces;

/**
 * Created by paulolosbanos on 8/31/17.
 */

public interface RegisterSaleView {
    void enableBackButton(boolean condition);
    void enableNextButton(boolean condition);
    void enableLoading(boolean condition);
    void onSuccess();
    void setFinalButton();
}
