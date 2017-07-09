package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.Product;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.ProductOrderPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.ProductOrderView;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProductOrderPresenterImpl implements ProductOrderPresenter {

    private ProductOrderView mView;
    private Retrofit mRetrofit;

    public ProductOrderPresenterImpl(ProductOrderView mView, Retrofit mRetrofit) {
        this.mView = mView;
        this.mRetrofit = mRetrofit;
    }

    @Override
    public void loadProducts() {
        MainService service = mRetrofit.create(MainService.class);
        service.getProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    LoggerUtil.log(response);
                    mView.fillRecyclerView(response.getProducts());
                });
    }
}
