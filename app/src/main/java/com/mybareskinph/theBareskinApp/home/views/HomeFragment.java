package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.BareskinApplication;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.services.MainService;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;
import com.mybareskinph.theBareskinApp.util.Money;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends Fragment{

    @BindView(R.id.tv_current_supply_worth)
    TextView currentSupplyWorth;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    private Unbinder unbinder;

    protected void bindView(@NonNull Object target, @NonNull View source) {
        unbinder = ButterKnife.bind(target, source);
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
        MainService service = ((BareskinApplication) getActivity().getApplication()).retrofit.create(MainService.class);

        service.login()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    currentSupplyWorth.setText(Money.formatPrice("PHP",loginResponse.getStoreValue()));
                });

    }
}
