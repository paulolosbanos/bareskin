package com.mybareskinph.theBareskinApp.home.implementations;

import android.util.Log;

import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;
import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.NewOrderPresenter;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.NewOrderView;
import com.mybareskinph.theBareskinApp.home.views.FormFragments;
import com.mybareskinph.theBareskinApp.home.views.OrderInfoFragment;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;

import java.util.List;
import java.util.Objects;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by paulolosbanos on 8/12/17.
 */

public class NewOrderPresenterImpl implements NewOrderPresenter {

    private OrderRequest orderRequest = new OrderRequest();
    private NewOrderView mView;
    private Retrofit mRetrofit;
    private UserCredential userCredential;

    public static final int PRODUCT_ORDERS = 0;
    public static final int PAYMENT_INFO = 1;
    public static final int CUSTOMER_INFO = 2;

    public NewOrderPresenterImpl() {

    }

    public NewOrderPresenterImpl(NewOrderView mView, Retrofit retrofit, UserCredential userCredentials) {
        this.mView = mView;
        this.mRetrofit = retrofit;
        this.userCredential = userCredentials;
    }

    public void setOrderList(List<OrderUnit> orderList) {
        orderRequest.setOrderUnitList(orderList);
    }

    public boolean saveOrderInfo(int page, Object payload) {
        switch (page) {
            case PRODUCT_ORDERS:
                orderRequest.setOrderUnitList((List<OrderUnit>) payload);
                return !orderRequest.getOrderUnitList().isEmpty();
            case PAYMENT_INFO:
                String[] payment_info = ((String) payload).split(":");
                orderRequest.setPaymentChannel(payment_info[0]);
                orderRequest.setPaymentDate(payment_info[1]);
                return !orderRequest.getPaymentChannel().isEmpty() && !orderRequest.getPaymentDate().isEmpty();
            case CUSTOMER_INFO:
                String[] customer_info = ((String) payload).split(";");
                orderRequest.setName(customer_info[0]);
                orderRequest.setAddress(customer_info[1]);
                orderRequest.setMobile(customer_info[2]);
                return !orderRequest.getAddress().isEmpty() && !orderRequest.getMobile().isEmpty();
        }
        return false;
    }

    @Override
    public void submitOrder() {
        mView.enableLoading(orderRequest != null);
        MainService service = mRetrofit.create(MainService.class);
        service
                .submitOrder(userCredential.getUid(),orderRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newOrderResponse -> {
                    mView.orderPlacementSuccess(newOrderResponse);
                    mView.enableLoading(false);
                    mView.setFinalButton();
                });
    }

    public void updateView(FormFragments currentFragment) {
        if (currentFragment instanceof OrderInfoFragment) {
            ((OrderInfoFragment) currentFragment).requestObservable().onNext(orderRequest);
        }
    }

    public void updateNavigationButtons(boolean answered) {
        mView.enableNextButton(answered);
        mView.enableBackButton(answered);
    }
}
