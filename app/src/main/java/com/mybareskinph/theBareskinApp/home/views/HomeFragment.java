package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.App;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.presenters.HomePresenter;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomeView;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;
import com.mybareskinph.theBareskinApp.util.Money;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment implements HomeView{

    @BindView(R.id.tv_current_supply_worth)
    TextView currentSupplyWorth;

    @BindView(R.id.tv_future_earning)
    TextView futureEarning;

    @BindView(R.id.ll_invite_code)
    LinearLayout inviteCodeContainer;

    @BindView(R.id.tv_invite_code)
    TextView inviteCode;

    @BindView(R.id.pb_loading_invite)
    ProgressBar loadingInvite;

    @BindView(R.id.pb_loading_current_supply_worth)
    ProgressBar loadingCurrentSupply;

    @BindView(R.id.pb_loading_future_earning)
    ProgressBar loadingFutureEarning;

    @Inject
    Retrofit retrofit;

    HomePresenter presenter;

    public HomeFragment() {
        presenter = new HomePresenter(this);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_home, container, false);
        bindView(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.login();
    }

    @Override
    public void onLogin() {
        MainService svc = getRetrofit().create(MainService.class);
        svc.login()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof HttpException) {
                            LoggerUtil.log(((HttpException) e).code());
                        } else if (e instanceof IOException) {
                            LoggerUtil.log(e);
                        }
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        loadingCurrentSupply.setVisibility(View.GONE);
                        currentSupplyWorth.setVisibility(View.VISIBLE);

                        loadingInvite.setVisibility(View.GONE);
                        inviteCodeContainer.setVisibility(View.VISIBLE);

                        currentSupplyWorth.setText(Money.formatPrice("PHP",loginResponse.getStoreValue()));
                        inviteCode.setText(loginResponse.getUserCredential().getUid());
                    }
                });
    }
}
