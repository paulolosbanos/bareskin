package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.adapters.SupplyAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.SupplyPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.SupplyView;
import com.mybareskinph.theBareskinApp.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;

public class SupplyFragment extends BaseFragment implements SupplyView {

    @BindView(R.id.rv_supplies)
    RecyclerView suppliesList;

    ArrayList<StoreItem> items;
    SupplyPresenterImpl presenter;

    public SupplyFragment() {
    }

    public static SupplyFragment newInstance(ArrayList<StoreItem> items) {
        SupplyFragment fragment = new SupplyFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.ITEMS, items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_supply, container, false);
        bindView(this, view);

        if (getArguments() != null) {
            items = getArguments().getParcelableArrayList(Constants.ITEMS);
        }
        ((HomeActivity) getActivity()).changeToolbarTitle("Supplies");

        presenter = new SupplyPresenterImpl(this, getRetrofit());
        SupplyAdapter adapter = new SupplyAdapter(getContext(), items);
        suppliesList.setLayoutManager(new LinearLayoutManager(getContext()));
        suppliesList.setAdapter(adapter);

        return view;
    }
}
