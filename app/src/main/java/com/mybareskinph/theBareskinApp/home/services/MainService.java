package com.mybareskinph.theBareskinApp.home.services;


import com.mybareskinph.theBareskinApp.home.pojos.GetProductsResponse;
import com.mybareskinph.theBareskinApp.home.pojos.LoginRequest;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface MainService {

    @POST("/login") // will change to POST
    Observable<LoginResponse> login(@Body LoginRequest id); // will add parameter

    @GET("/products")
    Observable<GetProductsResponse> getProducts();
}
