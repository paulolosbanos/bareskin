package com.mybareskinph.theBareskinApp.sale;

import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleRequest;
import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleResponse;
import com.mybareskinph.theBareskinApp.sale.pojos.SalesHistoryResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by paulolosbanos on 9/3/17.
 */

public interface SaleService {

    @POST("/sale/submit")
    Observable<RegisterSaleResponse> registerSale(@Body RegisterSaleRequest request);

    @GET("/sale/history/{userId}")
    Observable<SalesHistoryResponse> getSalesHistory(@Path("userId") String userId);
}
