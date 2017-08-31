package com.mybareskinph.theBareskinApp.home.services;


import com.mybareskinph.theBareskinApp.home.pojos.GetProductsResponse;
import com.mybareskinph.theBareskinApp.home.pojos.LoginRequest;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;
import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface MainService {

    @POST("/login")
    Observable<LoginResponse> login(@Body LoginRequest id);

    @GET("/products")
    Observable<GetProductsResponse> getProducts();

    @POST("/order/submit/{userId}")
    Observable<NewOrderResponse> submitOrder(@Path("userId") String userId, @Body OrderRequest request);
}
