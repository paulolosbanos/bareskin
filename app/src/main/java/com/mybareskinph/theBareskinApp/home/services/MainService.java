package com.mybareskinph.theBareskinApp.home.services;


import com.mybareskinph.theBareskinApp.home.pojos.GetProductsResponse;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface MainService {

    @GET("/login") // will change to POST
    Observable<LoginResponse> login(); // will add parameter

    @GET("/products")
    Observable<GetProductsResponse> getProducts();
}
