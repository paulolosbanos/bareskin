package com.mybareskinph.theBareskinApp.home.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.util.Money;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Order extends LinearLayout {

    @BindView(R.id.item_name)
    TextView itemName;

    @BindView(R.id.item_qty)
    TextView itemQty;

    @BindView(R.id.item_cost_unit)
    TextView itemCostUnit;

    @BindView(R.id.item_total_cost)
    TextView itemTotalCost;

    public Order(Context context, StoreItem item) {
        super(context);
        init(context, item);
    }

    public Order(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(null, null);
    }

    public Order(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(null, null);
    }

    public void init(Context context, StoreItem item) {
        inflate(getContext(), R.layout.item_order_list, this);
        ButterKnife.bind(this);

        if (item != null) {
            itemName.setText(item.getItemName());
            itemQty.setText(context.getString(R.string.label_x_pcs_ordered, String.valueOf(item.getItemQty())));
            itemCostUnit.setText(Money.formatPrice(Money.PHILIPPINE_PESO, item.getItemCostUnit()));
            itemTotalCost.setText(Money.formatPrice(Money.PHILIPPINE_PESO, item.getItemQty() * item.getItemCostUnit()));
        }
    }
}
