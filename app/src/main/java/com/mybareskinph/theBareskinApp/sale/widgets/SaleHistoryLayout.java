package com.mybareskinph.theBareskinApp.sale.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.StoreItem;
import com.mybareskinph.theBareskinApp.sale.pojos.SalesHistory;
import com.mybareskinph.theBareskinApp.util.Money;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleHistoryLayout extends LinearLayout {

    public SaleHistoryLayout(Context context, OrderUnit history) {
        super(context);
        init(context, history);
    }

    public SaleHistoryLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(null, null);
    }

    public SaleHistoryLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(null, null);
    }

    public void init(Context context, OrderUnit history) {
        inflate(getContext(), R.layout.item_sales_history_list, this);
        ButterKnife.bind(this);
    }
}
