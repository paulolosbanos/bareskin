package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.util.Money;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements SectionIndexer {

    private List<StoreOrder> orderList;
    private final LayoutInflater inflater;

    public OrderAdapter(Context context, List<StoreOrder> orders) {
        this.orderList = orders;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StoreOrder item = orderList.get(i);
        /*
        viewHolder.itemName.setText(item.getItemName());
        viewHolder.itemQty.setText(item.getItemQty() + " pcs in stock");
        viewHolder.itemCostPerUnit.setText(Money.formatPrice(Money.PHILIPPINE_PESO, item.getItemCostUnit()));
        viewHolder.itemSrpPerUnit.setText(Money.formatPrice(Money.PHILIPPINE_PESO, item.getItemSrpUnit()));
        viewHolder.itemTotalCost.setText(Money.formatPrice(Money.PHILIPPINE_PESO, item.getItemCostUnit() * item.getItemQty()));
        viewHolder.itemTotalRevenue.setText(Money.formatPrice(Money.PHILIPPINE_PESO, item.getItemSrpUnit() * item.getItemQty()));
        */
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_name)
        TextView itemName;

        @BindView(R.id.item_qty)
        TextView itemQty;

        @BindView(R.id.item_cost_unit)
        TextView itemCostPerUnit;

        @BindView(R.id.item_srp_unit)
        TextView itemSrpPerUnit;

        @BindView(R.id.item_total_cost)
        TextView itemTotalCost;

        @BindView(R.id.item_total_revenue)
        TextView itemTotalRevenue;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
