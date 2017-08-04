package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.Product;
import com.mybareskinph.theBareskinApp.util.Money;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private final LayoutInflater inflater;
    private Context mContext;
    private final PublishSubject<OrderUnit> stateSubject;



    public ProductAdapter(Context context, List<Product> items, PublishSubject<OrderUnit> stateSubject) {
        this.productList = items;
        this.stateSubject = stateSubject;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_product_order, viewGroup, false);
        return new ViewHolder(view);
    }

    private void increaseItemCount(TextView viewCount, ViewHolder viewHolder) {
        viewCount.setText(String.valueOf(++viewHolder.itemCount));

    }


    private void removeItem(ViewHolder viewHolder) {
        viewHolder.itemCount = 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Product product = productList.get(i);
        viewHolder.name.setText(product.getProductName());
        viewHolder.price.setText(Money.formatPrice(Money.PHILIPPINE_PESO, product.getProductCostUnit()));

        RxView.clicks(viewHolder.itemArea).subscribe(aVoid -> {
            increaseItemCount(viewHolder.viewCount,viewHolder);
            stateSubject.onNext(new OrderUnit(product,viewHolder.itemCount));
            viewHolder.count.setVisibility(View.VISIBLE);
            viewHolder.remove.setVisibility(View.VISIBLE);
        });

        RxView.clicks(viewHolder.remove).subscribe(aVoid -> {
            removeItem(viewHolder);
            stateSubject.onNext(new OrderUnit(product,viewHolder.itemCount));
            viewHolder.count.setVisibility(View.GONE);
            viewHolder.remove.setVisibility(View.GONE);
        });

        Picasso.with(mContext)
                .load("http://image.ibb.co/e2Vc0Q/placeholder_image.png")
                .into(viewHolder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        viewHolder.loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_name)
        TextView name;

        @BindView(R.id.item_price)
        TextView price;

        @BindView(R.id.product_image)
        ImageView image;

        @BindView(R.id.loading)
        ProgressBar loading;

        @BindView(R.id.rl_count)
        RelativeLayout count;

        @BindView(R.id.ll_item_area)
        LinearLayout itemArea;

        @BindView(R.id.rl_remove)
        RelativeLayout remove;

        @BindView(R.id.tv_count)
        TextView viewCount;

        private int itemCount = 0;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
