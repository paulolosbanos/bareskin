package com.mybareskinph.theBareskinApp.sale.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.adapters.OrderAdapter;
import com.mybareskinph.theBareskinApp.home.adapters.PriceBreakdownAdapter;
import com.mybareskinph.theBareskinApp.home.pojos.OrderRequest;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.widgets.Order;
import com.mybareskinph.theBareskinApp.sale.pojos.SalesHistory;
import com.mybareskinph.theBareskinApp.sale.widgets.SaleHistoryLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulolosbanos on 9/7/17.
 */

public class SalesHistoryAdapter extends RecyclerView.Adapter<SalesHistoryAdapter.ViewHolder> {

    private List<SalesHistory> salesHistories;
    private final LayoutInflater inflater;
    private Context context;

    public SalesHistoryAdapter(Context context, List<SalesHistory> salesHistoryList) {
        this.salesHistories = salesHistoryList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_sales_history, viewGroup, false);
        return new SalesHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        SalesHistory data = salesHistories.get(position);

        viewHolder.refNo.setText(String.format(context.getString(R.string.label_ref_colon_value), data.getReferenceNumber()));

        if(data.getBuyerName() == null) {
            viewHolder.buyerName.setVisibility(View.GONE);
        } else {
            viewHolder.buyerName.setText(String.format(context.getString(R.string.label_buyer_name_colon_value), data.getBuyerName()));
        }
        if(data.getBuyerNumber() == null) {
            viewHolder.buyerNumber.setVisibility(View.GONE);
        } else {
            viewHolder.buyerNumber.setText(String.format(context.getString(R.string.label_buyer_number_colon_value), data.getBuyerNumber()));
        }

        PriceBreakdownAdapter priceBreakdownAdapter = new PriceBreakdownAdapter(context, getPriceBreakdown(data.getBuyerPurchases()));
        viewHolder.salesList.setAdapter(priceBreakdownAdapter);
        viewHolder.salesList.setLayoutManager(new LinearLayoutManager(context));

        viewHolder.transactionDate.setText(String.format(context.getString(R.string.label_transaction_date_colon_value),data.getPaymentDate()));
    }

    public List<String> getPriceBreakdown(List<OrderUnit> histories) {
        List<String> breakdown = new ArrayList<>();
        int total = 0;

        for (OrderUnit unit : histories) {
            int value = unit.getProduct().getProductSrpUnit() * unit.getQuantity();
            breakdown.add(unit.getProduct().getProductName() + " ( × " + unit.getQuantity() + " )" + ";" + value);
            total += value;
        }
        breakdown.add("line");
        breakdown.add("boldTotal Sale;" + (total));
        return breakdown;
    }

    @Override
    public int getItemCount() {
        return salesHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_sales_list)
        RecyclerView salesList;

        @BindView(R.id.tv_buyer_name)
        TextView buyerName;

        @BindView(R.id.tv_buyer_number)
        TextView buyerNumber;

        @BindView(R.id.tv_ref_no)
        TextView refNo;

        @BindView(R.id.tv_transaction_date)
        TextView transactionDate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
