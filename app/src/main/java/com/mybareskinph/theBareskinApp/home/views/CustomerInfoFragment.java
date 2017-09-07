package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.util.Observables;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.subjects.PublishSubject;

public class CustomerInfoFragment extends FormFragments {

    @BindView(R.id.et_name)
    EditText name;

    @BindView(R.id.et_address)
    EditText address;

    @BindView(R.id.et_number)
    EditText number;

    PublishSubject<String> customerInfo = PublishSubject.create();

    public CustomerInfoFragment() {
    }

    public static CustomerInfoFragment newInstance() {
        CustomerInfoFragment fragment = new CustomerInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_info, container, false);
        bindView(this, view);

        name.setText(getUserCredentials().getFullname());

        List<Observable<Boolean>> observableList = Arrays.asList(
                RxTextView.textChanges(name).map(charSequence -> !charSequence.toString().isEmpty()),
                RxTextView.textChanges(address).map(charSequence -> !charSequence.toString().isEmpty()),
                RxTextView.textChanges(number).map(charSequence -> !charSequence.toString().isEmpty()));

        Observables
                .allLatestTrue(observableList)
                .subscribe(aBoolean -> {
                    if(aBoolean)
                        customerInfo.onNext(name.getText().toString() + ";" + address.getText().toString() + ";" + number.getText().toString());
                });
        return view;
    }

    public PublishSubject<String> customerInfo() {
        return customerInfo;
    }
}
