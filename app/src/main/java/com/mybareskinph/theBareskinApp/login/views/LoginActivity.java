package com.mybareskinph.theBareskinApp.login.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseActivity;
import com.mybareskinph.theBareskinApp.home.implementations.LoginPresenterImpl;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.LoginView;
import com.mybareskinph.theBareskinApp.home.views.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.blurry.Blurry;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.iv_bg)
    ImageView background;

    @BindView(R.id.btn_login)
    RelativeLayout login;

    @BindView(R.id.tv_id)
    EditText id;

    @BindView(R.id.tv_password)
    EditText pass;

    @BindView(R.id.pb_loading)
    ProgressBar loading;

    @BindView(R.id.tv_login_label)
    TextView loginLabel;

    LoginPresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Blurry.with(this)
                .async()
                .sampling(5)
                .color(Color.argb(80, 55, 54, 30))
                .from(((BitmapDrawable) background.getDrawable()).getBitmap())
                .into(background);

        presenter = new LoginPresenterImpl(this, getRetrofit());

        RxTextView.textChanges(id).subscribe(id -> presenter.setId(id.toString()));
        RxTextView.textChanges(pass).subscribe(pass -> presenter.setPassword(pass.toString()));

        RxView.clicks(login).subscribe(aVoid -> presenter.login());
    }

    @Override
    public void loginLoading() {
        loading.setVisibility(View.VISIBLE);
        loginLabel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showHome() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}
