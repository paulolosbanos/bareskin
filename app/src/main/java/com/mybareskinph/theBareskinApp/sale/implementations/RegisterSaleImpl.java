package com.mybareskinph.theBareskinApp.sale.implementations;

import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.views.FormFragments;
import com.mybareskinph.theBareskinApp.sale.SaleService;
import com.mybareskinph.theBareskinApp.sale.pojos.RegisterSaleRequest;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.RegisterSalePresenter;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.RegisterSaleView;
import com.mybareskinph.theBareskinApp.sale.views.RegisterSaleInfoFragment;

import java.util.List;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterSaleImpl implements RegisterSalePresenter {
    RegisterSaleView mView;
    RegisterSaleRequest request = new RegisterSaleRequest();
    Retrofit mRetrofit;

    public RegisterSaleImpl(RegisterSaleView mView, Retrofit retrofit) {
        this.mView = mView;
        this.mRetrofit = retrofit;
    }

    public boolean saveRequestInfo(int page, Object payload) {
        switch (page) {
            case 0:
                String[] payloadString = ((String) payload).split(":");

                switch (payloadString[0]) {
                    case "name":
                        request.setBuyerName(payloadString.length < 2 ? null : payloadString[1]);
                        break;
                    case "number":
                        request.setBuyerNumber(payloadString.length < 2 ? null : payloadString[1]);
                        break;
                }
                return true;
            case 1:
                if (payload instanceof List)
                    request.setPurchases((List<OrderUnit>) payload);
                return !request.getPurchases().isEmpty();
        }
        return false;
    }

    public void updateView(FormFragments currentFragment) {
        if (currentFragment instanceof RegisterSaleInfoFragment) {
            ((RegisterSaleInfoFragment) currentFragment).requestObservable().onNext(request);
        }
    }

    public void submitSale() {
        mView.enableLoading(request != null);
        SaleService service = mRetrofit.create(SaleService.class);
        service
                .registerSale(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registerSaleResponse -> {
                    if (registerSaleResponse.getStatus().equals("success")) {
                        mView.onSuccess();
                        mView.enableLoading(false);
                        mView.setFinalButton();
                    }
                });
    }

    public void updateNavigationButtons(boolean answered) {
        mView.enableNextButton(answered);
        mView.enableBackButton(answered);
    }
}
