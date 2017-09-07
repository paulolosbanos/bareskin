package com.mybareskinph.theBareskinApp.sale.views;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseActivity;
import com.mybareskinph.theBareskinApp.sale.adapter.RegisterSaleAdapter;
import com.mybareskinph.theBareskinApp.sale.implementations.RegisterSaleImpl;
import com.mybareskinph.theBareskinApp.sale.viewinterfaces.RegisterSaleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 8/31/17.
 */

public class RegisterSaleActivity extends BaseActivity implements RegisterSaleView {

    @BindView(R.id.vp_new_order)
    ViewPager registerSalePager;

    @BindView(R.id.iv_back)
    ImageView back;

    @BindView(R.id.ll_next)
    LinearLayout next;

    @BindView(R.id.ll_loading)
    LinearLayout loading;

    @BindView(R.id.tv_fwd_btn)
    TextView forwardButton;

    @BindView(R.id.iv_right_done)
    ImageView rightDone;

    @BindView(R.id.iv_right)
    ImageView right;

    RegisterSaleAdapter adapter;
    PublishSubject<Object> answerWatcher = PublishSubject.create();
    RegisterSaleImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sale);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.darkerBrown));
        }
        adapter = new RegisterSaleAdapter(getSupportFragmentManager(), answerWatcher);
        registerSalePager.setAdapter(adapter);
        presenter = new RegisterSaleImpl(this, getRetrofit());

        enableBackButton(false);
        enableNextButton(false);

        RxViewPager
                .pageSelections(registerSalePager)
                .map(pos -> (adapter.getRegisteredFragment(pos)))
                .filter(formFragments -> formFragments != null)
                .subscribe(currentFragment -> {
                    presenter.updateNavigationButtons(currentFragment.isAnswered());
                    presenter.updateView(currentFragment);
                });

        answerWatcher
                .asObservable()
                .subscribe(payload -> presenter.updateNavigationButtons(presenter.saveRequestInfo(registerSalePager.getCurrentItem(), payload)));
    }

    @OnClick(R.id.ll_next)
    public void next(View view) {
        if (registerSalePager.getCurrentItem() == 2) {
            presenter.submitSale();
        } else {
            registerSalePager.setCurrentItem(registerSalePager.getCurrentItem() + 1);
        }
    }

    @OnClick(R.id.iv_back)
    public void back(View view) {
        registerSalePager.setCurrentItem(registerSalePager.getCurrentItem() - 1);
    }

    @OnClick(R.id.iv_right_done)
    public void finishButton(View view) {
        finish();
    }

    @Override
    public void enableBackButton(boolean condition) {
        back.setEnabled(registerSalePager.getCurrentItem() > 0 || condition);
        back.setClickable(registerSalePager.getCurrentItem() > 0 || condition);
    }

    @Override
    public void enableLoading(boolean condition) {
        loading.setVisibility(condition ? View.VISIBLE : View.GONE);
        next.setVisibility(condition ? View.GONE : View.VISIBLE);
    }

    @Override
    public void enableNextButton(boolean condition) {
        next.setEnabled(condition);
        next.setClickable(condition);
    }

    @Override
    public void setFinalButton() {
        forwardButton.setVisibility(View.GONE);
        rightDone.setVisibility(View.VISIBLE);
        right.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        registerSalePager.setCurrentItem(adapter.getCount() - 1);
    }
}
