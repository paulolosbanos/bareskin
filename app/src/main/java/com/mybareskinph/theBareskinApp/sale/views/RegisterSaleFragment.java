package com.mybareskinph.theBareskinApp.sale.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.views.FormFragments;

import butterknife.BindView;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 8/31/17.
 */

public class RegisterSaleFragment extends FormFragments {

    @BindView(R.id.ms_payment_method)
    MaterialSpinner paymentMethod;

    @BindView(R.id.et_buyer_name)
    EditText buyerName;

    @BindView(R.id.et_buyer_number)
    EditText buyerNumber;

    PublishSubject<String> buyerNameObservable = PublishSubject.create();
    PublishSubject<String> buyerNumberObservable = PublishSubject.create();

    public static RegisterSaleFragment newInstance() {
        return new RegisterSaleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_sale, container, false);
        bindView(this, view);

        paymentMethod.setItems("I made the sale today", "I forgot to enter it the last time");

        RxTextView.textChanges(buyerName).map(CharSequence::toString).subscribe(buyerNameObservable::onNext);
        RxTextView.textChanges(buyerNumber).map(CharSequence::toString).subscribe(buyerNumberObservable::onNext);

        return view;
    }

    public Observable<String> getBuyerInfoObservable() {
        return Observable.merge(
                getBuyerNameObservable().map(s -> "name:"+s),
                getBuyerNumberObservable().map(s -> "number:"+s));
    }

    public PublishSubject<String> getBuyerNameObservable() {
        return buyerNameObservable;
    }

    public PublishSubject<String> getBuyerNumberObservable() {
        return buyerNumberObservable;
    }
}
