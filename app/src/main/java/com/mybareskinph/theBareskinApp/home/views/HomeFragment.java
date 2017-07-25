package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseActivity;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.implementations.HomePresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.LoginResponse;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.HomeView;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.Money;
import com.mybareskinph.theBareskinApp.util.StoreComputationUtil;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeView {

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

    @BindView(R.id.tv_sales_history)
    TextView salesHistory;

    @BindView(R.id.tv_sell_now)
    TextView sellNow;

    @BindView(R.id.tv_details)
    TextView details;

    @BindView(R.id.tv_order_now)
    TextView orderNow;

    HomePresenterImpl presenter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_home, container, false);
        bindView(this, view);
        presenter = new HomePresenterImpl(this, getGlobalObjects());
        details.setOnClickListener(view1 -> presenter.onDetailsClick());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getGlobalObjects();
        changeToolbarTitle("Home");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showFutureEarning(ArrayList<StoreItem> items) {
        loadingFutureEarning.setVisibility(View.GONE);
        futureEarning.setVisibility(View.VISIBLE);
        futureEarning.setText(Money.formatPrice(Money.PHILIPPINE_PESO, StoreComputationUtil.computeEarningTrajectory(items)));
        salesHistory.setEnabled(true);
        sellNow.setEnabled(true);
    }

    @Override
    public void hideFutureEarning() {
        loadingFutureEarning.setVisibility(View.VISIBLE);
        futureEarning.setVisibility(View.GONE);
        salesHistory.setEnabled(false);
        sellNow.setEnabled(false);
    }

    @Override
    public void showSupplyWorth(ArrayList<StoreItem> items) {
        loadingCurrentSupply.setVisibility(View.GONE);
        currentSupplyWorth.setVisibility(View.VISIBLE);
        currentSupplyWorth.setText(Money.formatPrice(Money.PHILIPPINE_PESO, StoreComputationUtil.computeInventoryWorth(items)));
        details.setEnabled(true);
        orderNow.setEnabled(true);
    }

    @Override
    public void hideSupplyWorth() {
        loadingCurrentSupply.setVisibility(View.VISIBLE);
        currentSupplyWorth.setVisibility(View.GONE);
        details.setEnabled(false);
        orderNow.setEnabled(false);
    }

    @Override
    public void showInviteCode(UserCredential credential) {
        loadingInvite.setVisibility(View.GONE);
        inviteCodeContainer.setVisibility(View.VISIBLE);
        inviteCode.setText(credential.getUid());
    }

    @Override
    public void hideInviteCode() {
        loadingInvite.setVisibility(View.VISIBLE);
        inviteCodeContainer.setVisibility(View.GONE);

    }

    @Override
    public void goToSuppliesPage() {
        SupplyFragment fragment = SupplyFragment.newInstance((ArrayList<StoreItem>) getGlobalObjects().get(Constants.SUPPLIES));
        ((HomeActivity) getActivity()).changeCheckedNavTitle(R.id.nav_inventory);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

}
