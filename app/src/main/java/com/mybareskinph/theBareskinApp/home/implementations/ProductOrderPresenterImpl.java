package com.mybareskinph.theBareskinApp.home.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.GetProductsResponse;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.ProductOrderPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.ProductOrderView;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.http.DELETE;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;


public class ProductOrderPresenterImpl implements ProductOrderPresenter {

    private ProductOrderView mView;
    private Retrofit mRetrofit;
    public String currentTag = Constants.PRODUCTS_TAG_ALL;
    public static final int REMOVE = -1;
    public static final int ADD = -2;

    List<OrderUnit> orders = new ArrayList<>();

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
                .subscribe(new Subscriber<GetProductsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        mView.enableLoading(true);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(GetProductsResponse response) {
                        mView.enableLoading(false);
                        mView.fillRecyclerView(response.getProducts());
                        mView.fillTagsView(response.getTags());
                    }
                });
    }

    @Override
    public void updateOrder(OrderUnit orderUnit, PublishSubject<Integer> subject) {
        Observable.just(new ArrayList<>(orders))
                .flatMapIterable(list -> list)
                .switchIfEmpty(Observable.just(new OrderUnit()))
                .subscribe(order -> {
                    if (order.getProduct().getProductId() != null && (order.getProduct().getProductId().equals(orderUnit.getProduct().getProductId()))) {
                        if (orderUnit.getQuantity() == 0) {
                            orders.remove(orders.indexOf(order));
                            subject.onNext(REMOVE);
                        } else {
                            orders.get(orders.indexOf(order)).addQuantity();
                            subject.onNext(orders.indexOf(order));
                        }
                    } else {
                        if (!listContains(orderUnit) && orderUnit.getQuantity() != 0) {
                            orders.add(orderUnit);
                            subject.onNext(ADD);
                        }
                    }
                });
    }

    public List<OrderUnit> getOrder() {
        return orders;
    }

    private boolean listContains(OrderUnit unit) {
        for (OrderUnit ou : orders) {
            if (ou.getProduct().getProductId().equals(unit.getProduct().getProductId()))
                return true;
        }
        return false;
    }

    public void setCurrentTag(String tag) {
        this.currentTag = tag;
    }
}
