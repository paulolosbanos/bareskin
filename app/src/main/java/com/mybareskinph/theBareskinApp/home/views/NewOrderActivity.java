package com.mybareskinph.theBareskinApp.home.views;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseActivity;
import com.mybareskinph.theBareskinApp.home.adapters.NewOrderPagerAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.NewOrderPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;
import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.NewOrderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subjects.PublishSubject;

public class NewOrderActivity extends BaseActivity implements NewOrderView {

    @BindView(R.id.vp_new_order)
    ViewPager newOrderPager;

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

    NewOrderPresenterImpl presenter;

    PublishSubject<Object> answerWatcher = PublishSubject.create();
    NewOrderPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.darkerBrown));
        }

        presenter = new NewOrderPresenterImpl(this, getRetrofit(), getUserCredentials());

        adapter = new NewOrderPagerAdapter(getSupportFragmentManager(), answerWatcher);
        newOrderPager.setAdapter(adapter);

        enableNextButton(false);
        enableBackButton(false);

        RxViewPager
                .pageSelections(newOrderPager)
                .map(pos -> (adapter.getRegisteredFragment(pos)))
                .filter(formFragments -> formFragments != null)
                .subscribe(currentFragment -> {
                    presenter.updateNavigationButtons(currentFragment.isAnswered());
                    presenter.updateView(currentFragment);
                });

        answerWatcher
                .asObservable()
                .subscribe(payload -> presenter.updateNavigationButtons(presenter.saveOrderInfo(newOrderPager.getCurrentItem(), payload)));
    }

    @OnClick(R.id.ll_next)
    public void next(View view) {
        if (newOrderPager.getCurrentItem() == 3) {
            presenter.submitOrder();
        } else {
            newOrderPager.setCurrentItem(newOrderPager.getCurrentItem() + 1);
        }
    }

    @OnClick(R.id.iv_back)
    public void back(View view) {
        newOrderPager.setCurrentItem(newOrderPager.getCurrentItem() - 1);
    }

    @OnClick(R.id.iv_right_done)
    public void finishButton(View view) {
        finish();
    }

    @Override
    public void enableBackButton(boolean condition) {
        back.setEnabled(newOrderPager.getCurrentItem() > 0 || condition);
        back.setClickable(newOrderPager.getCurrentItem() > 0 || condition);
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
    public void orderPlacementSuccess(NewOrderResponse response) {
        ((OrderPlacementSuccessFragment) adapter.getRegisteredFragment(adapter.getCount() - 1)).responseSubject.onNext(response);
        newOrderPager.setCurrentItem(adapter.getCount() - 1);
    }

    @Override
    public void setFinalButton() {
        forwardButton.setVisibility(View.GONE);
        rightDone.setVisibility(View.VISIBLE);
        right.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }
}
