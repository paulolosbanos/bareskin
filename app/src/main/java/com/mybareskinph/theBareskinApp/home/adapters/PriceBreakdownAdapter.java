package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.util.LoggerUtil;
import com.mybareskinph.theBareskinApp.util.Money;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulolosbanos on 8/23/17.
 */

public class PriceBreakdownAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<String> pricebreakdown;
    private final int BREAKDOWN = 0;
    private final int BREAKDOWN_BOLD = 1;
    private final int LINE = 2;

    public PriceBreakdownAdapter(@NonNull Context context, List<String> pricebreakdown) {
        this.inflater = LayoutInflater.from(context);
        this.pricebreakdown = pricebreakdown;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == LINE) {
            view = inflater.inflate(R.layout.item_layout_line, parent, false);
            return new ViewHolderLine(view);
        } else if (viewType == BREAKDOWN_BOLD) {
            view = inflater.inflate(R.layout.item_layout_bold, parent, false);
            return new ViewHolderPriceBreakdown(view);
        } else {
            view = inflater.inflate(R.layout.item_layout_price_breakdown, parent, false);
            return new ViewHolderPriceBreakdown(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        if (!pricebreakdown.get(position).equals("line") || pricebreakdown.get(position).contains("bold")) {
            ViewHolderPriceBreakdown holder = (ViewHolderPriceBreakdown) h;
            String[] token = pricebreakdown.get(position).split(";");
            String trimmedString = token[0].replace("bold", "");
            holder.title.setText(trimmedString);
            try {
                holder.value.setText(Money.formatPrice(Money.PHILIPPINE_PESO, Double.parseDouble(token[1])));
            } catch (NumberFormatException e) {
                LoggerUtil.log("token[1] is not numeric. skip parsing");
                holder.value.setText(token[1]);
            }


        }
    }

    @Override
    public int getItemCount() {
        return pricebreakdown.size();
    }

    @Override
    public int getItemViewType(int position) {
        String x = pricebreakdown.get(position);
        if (pricebreakdown.get(position).equals("line")) {
            return LINE;
        } else if (pricebreakdown.get(position).contains("bold")) {
            return BREAKDOWN_BOLD;
        } else {
            return BREAKDOWN;
        }
    }

    public class ViewHolderPriceBreakdown extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_value)
        TextView value;

        public ViewHolderPriceBreakdown(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolderLine extends RecyclerView.ViewHolder {

        @BindView(R.id.line)
        View line;

        public ViewHolderLine(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
