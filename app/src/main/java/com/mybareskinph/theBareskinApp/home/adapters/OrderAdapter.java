package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.home.pojos.StoreOrder;
import com.mybareskinph.theBareskinApp.home.widgets.Order;
import com.mybareskinph.theBareskinApp.util.CalendarDate;
import com.mybareskinph.theBareskinApp.util.DateFormats;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements SectionIndexer {

    private List<StoreOrder> orderList;
    private final LayoutInflater inflater;
    private Context context;


    public OrderAdapter(Context context, List<StoreOrder> orders) {
        this.orderList = orders;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StoreOrder order = orderList.get(i);
        for (StoreItem item : order.getItems()) {
            viewHolder.orderListContainer.addView(new Order(context, item));
        }
        viewHolder.dateOrderNumber.setText(context.getString(R.string.label_x_hyphen_word_x,
                DateFormats.DATE_FORMAT_EMDYYYY.format(CalendarDate.fromString(order.getOrderStartDate()).toJavaDate()),
                "Order",
                order.getOrderId()));
        viewHolder.itemStatus.setText(order.getStatus());
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
        @BindView(R.id.ll_order_list)
        LinearLayout orderListContainer;

        @BindView(R.id.tv_date_order_number)
        TextView dateOrderNumber;

        @BindView(R.id.item_status)
        TextView itemStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
