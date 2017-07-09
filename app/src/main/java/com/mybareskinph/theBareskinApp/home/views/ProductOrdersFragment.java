package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.adapters.ProductAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.HomePresenterImpl;
import com.mybareskinph.theBareskinApp.home.implementations.ProductOrderPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.Product;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.ProductOrderView;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;

import java.util.List;

import butterknife.BindView;

public class ProductOrdersFragment extends BaseFragment implements ProductOrderView {

    @BindView(R.id.et_product_name)
    AutoCompleteTextView productName;

    @BindView(R.id.product_view)
    RecyclerView productView;

    String[] autoCompleteString = {"#0012 - Luminous Whitening Soap"};
    ProductOrderPresenterImpl presenter;

    public ProductOrdersFragment() {
    }

    public static ProductOrdersFragment newInstance() {
        ProductOrdersFragment fragment = new ProductOrdersFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_order, container, false);
        bindView(this, view);

        presenter = new ProductOrderPresenterImpl(this, getRetrofit());
        presenter.loadProducts();

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.layout_auto_complete, autoCompleteString);
        productName.setAdapter(adapter);
        return view;
    }

    @Override
    public void fillRecyclerView(List<Product> productList) {
        ProductAdapter adapter = new ProductAdapter(getContext(), productList);

        productView.setAdapter(adapter);
        productView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}
