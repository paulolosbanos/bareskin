package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.base.BaseFragment;
import com.mybareskinph.theBareskinApp.home.adapters.LocationMembersAdapter;
import com.mybareskinph.theBareskinApp.home.adapters.PriceBreakdownAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.OrderPlacementSuccessPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.LocationMembers;
import com.mybareskinph.theBareskinApp.home.pojos.NewOrderResponse;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.widgets.TypeFacedTextView;

import java.util.List;

import butterknife.BindView;
import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 8/28/17.
 */

public class AboutUsFragment extends BaseFragment {

    @BindView(R.id.rv_distributors)
    RecyclerView distributorsContainer;

    @BindView(R.id.rv_resellers)
    RecyclerView resellersContainer;

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    LocationMembersAdapter distributorAdapter;
    LocationMembersAdapter resellerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        bindView(this, view);

        List<LocationMembers> distributors = (List<LocationMembers>) getGlobalObjects().get(Constants.DISTRIBUTORS);
        List<LocationMembers> resellers = (List<LocationMembers>) getGlobalObjects().get(Constants.RESELLERS);

        distributorAdapter = new LocationMembersAdapter(getContext(), distributors);
        resellerAdapter = new LocationMembersAdapter(getContext(), resellers);

        distributorsContainer.setAdapter(distributorAdapter);
        distributorsContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        resellersContainer.setAdapter(resellerAdapter);
        resellersContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
