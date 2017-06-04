package com.mybareskinph.theBareskinApp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mybareskinph.theBareskinApp.App;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;

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

    public Retrofit getRetrofit() {
        return mActivity.mRetrofit;
    }
}
