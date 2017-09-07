package com.mybareskinph.theBareskinApp.sale;

import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleRequest;
import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by paulolosbanos on 9/3/17.
 */

public interface SaleService {

    @POST("/sale/submit")
    Observable<RegisterSaleResponse> registerSale(@Body RegisterSaleRequest request);
}
