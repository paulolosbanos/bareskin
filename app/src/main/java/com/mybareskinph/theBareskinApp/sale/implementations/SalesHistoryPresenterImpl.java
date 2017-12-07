package com.mybareskinph.theBareskinApp.sale.implementations;

import com.mybareskinph.theBareskinApp.sale.SaleService;
import com.mybareskinph.theBareskinApp.sale.pojos.SalesHistory;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.SalesHistoryPresenter;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.SalesHistoryView;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by paulolosbanos on 9/7/17.
 */

public class SalesHistoryPresenterImpl implements SalesHistoryPresenter {
    SalesHistoryView mView;
    Retrofit mRetrofit;

    public SalesHistoryPresenterImpl(SalesHistoryView mView, Retrofit retrofit) {
        this.mView = mView;
        this.mRetrofit = retrofit;
        loadSalesHistory();
    }

    private void loadSalesHistory() {
        SaleService service = mRetrofit.create(SaleService.class);
        service
                .getSalesHistory(mView.userId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(salesHistoryResponse -> {
                    if(salesHistoryResponse.getStatus().equals("success")) {
                        mView.loadSalesHistory(salesHistoryResponse.getSalesHistory());
                    }
                });
    }
}
