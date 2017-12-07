package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.LocationMembers;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulolosbanos on 9/10/17.
 */

public class LocationMembersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<String> source;

    private final int LOCATION = 0;
    private final int NAME = 1;

    public LocationMembersAdapter(Context context, List<LocationMembers> source) {
        this.inflater = LayoutInflater.from(context);
        this.source = translateData(source);
    }

    private List<String> translateData(List<LocationMembers> source) {
        List<String> stringSource = new ArrayList<>();

        for (LocationMembers data : source) {
            stringSource.add(">" + data.getLocation());
            for (String name : data.getNames()) {
                stringSource.add(name);
            }
        }

        return stringSource;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == LOCATION) {
            view = inflater.inflate(R.layout.item_layout_location, parent, false);
            return new ViewHolderLocation(view);
        } else if (viewType == NAME) {
            view = inflater.inflate(R.layout.item_layout_member_name, parent, false);
            return new ViewHolderName(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String data = source.get(position);

        switch (holder.getItemViewType()) {
            case LOCATION:
                ViewHolderLocation vhLocation = (ViewHolderLocation) holder;
                vhLocation.location.setText(data.replace(">",""));
                break;
            case NAME:
                ViewHolderName vhName = (ViewHolderName) holder;
                vhName.name.setText(data);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    @Override
    public int getItemViewType(int position) {
        String data = source.get(position);
        if (data.contains(">")) {
            return LOCATION;
        } else {
            return NAME;
        }
    }

    public class ViewHolderLocation extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView location;

        public ViewHolderLocation(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolderName extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView name;

        public ViewHolderName(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
