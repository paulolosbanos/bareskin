package com.mybareskinph.theBareskinApp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mybareskinph.theBareskinApp.home.pojos.UserCredential;
import com.mybareskinph.theBareskinApp.home.views.HomeActivity;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private BaseActivity mActivity;

    public BaseFragment() {}

    protected void bindView(@NonNull Object target, @NonNull View source) {
        unbinder = ButterKnife.bind(target, source);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    public void changeToolbarTitle(String title) {
        ((HomeActivity) getActivity()).changeToolbarTitle(title);
    }

    public Retrofit getRetrofit() {
        return mActivity.mRetrofit;
    }

    public HashMap<String, Object> getGlobalObjects() {
        return ((BaseActivity) getActivity()).getGlobalObjects();
    }

    public UserCredential getUserCredentials() {
        return (UserCredential) ((BaseActivity) getActivity()).getGlobalObjects().get(Constants.USER_INFO);
    }

    @NonNull
    public final <T> Observable.Transformer<T, T> bind() {
        return observable -> observable
                .compose(mActivity.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
