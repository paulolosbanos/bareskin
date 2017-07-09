package com.mybareskinph.theBareskinApp.home.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.adapters.OrderAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.OrderPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.OrderView;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderFragment extends BaseFragment implements OrderView {

    @BindView(R.id.rv_supplies)
    RecyclerView orderList;


    ArrayList<StoreOrder> items;
    OrderPresenterImpl presenter;

    public OrderFragment() {
    }

    public static OrderFragment newInstance(ArrayList<StoreOrder> orders) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.ORDERS, orders);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_order, container, false);
        bindView(this, view);

        if (getArguments() != null) {
            items = getArguments().getParcelableArrayList(Constants.ORDERS);
        }
        ((HomeActivity) getActivity()).changeToolbarTitle("Orders");
        presenter = new OrderPresenterImpl(this, getRetrofit());
        OrderAdapter adapter = new OrderAdapter(getContext(), items);
        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.fab_add_order)
    public void addOrder(View view) {
        startActivity(new Intent(getContext(), NewOrderActivity.class));
    }
}
