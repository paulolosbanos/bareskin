package com.mybareskinph.theBareskinApp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.Product;
import com.mybareskinph.theBareskinApp.home.views.ProductOrdersFragment;
import com.mybareskinph.theBareskinApp.util.Constants;
import com.mybareskinph.theBareskinApp.util.Money;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<OrderUnit> productList;
    private List<OrderUnit> allProducts;
    private final LayoutInflater inflater;
    private Context mContext;
    private final PublishSubject<OrderUnit> stateSubject;
    private int mode;

    public ProductAdapter(Context context, List<Product> items, PublishSubject<OrderUnit> stateSubject, int mode) {
        List<OrderUnit> orderList = getConvertedItems(items);
        this.productList = orderList;
        this.allProducts = new ArrayList<>(orderList);
        this.stateSubject = stateSubject;
        this.mode = mode;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    private List<OrderUnit> getConvertedItems(List<Product> items) {
        List<OrderUnit> listOrder = new ArrayList<>();
        for (Product p : items) {
            listOrder.add(new OrderUnit(p, 0));
        }
        return listOrder;
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

    public void showProductTaggedAs(String tag, List<OrderUnit> order) {
        final List<OrderUnit> taggedProducts = new ArrayList<>();

        switch (tag) {
            case Constants.PRODUCTS_TAG_ALL:
                for (OrderUnit unit : order) {
                    taggedProducts.add(unit);
                }
                for (OrderUnit orderUnit : allProducts) {
                    if (!listContains(orderUnit, taggedProducts)) {
                        taggedProducts.add(orderUnit);
                    }
                }
                break;
            case Constants.PRODUCTS_TAG_INCLUDED:
                Observable.just(order)
                        .flatMapIterable(orderUnits -> orderUnits)
                        .subscribe(orderUnit -> taggedProducts.add(orderUnit));
                break;
            default:
                for (OrderUnit unit : order) {
                    if (unit.getProduct().getTags().contains(tag)) {
                        taggedProducts.add(unit);
                    }
                }
                for (OrderUnit orderUnit : allProducts) {
                    if (orderUnit.getProduct().getTags().contains(tag) && !listContains(orderUnit, taggedProducts)) {
                        taggedProducts.add(orderUnit);
                    }
                }
        }

        this.productList = taggedProducts;
        notifyDataSetChanged();
    }

    private boolean listContains(OrderUnit unit, List<OrderUnit> orders) {
        for (OrderUnit ou : orders) {
            if (ou.getProduct().getProductId().equals(unit.getProduct().getProductId()))
                return true;
        }
        return false;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        OrderUnit unit = productList.get(i);
        Product product = productList.get(i).getProduct();
        viewHolder.name.setText(product.getProductName());
        if(mode == ProductOrdersFragment.NEW_ORDER_MODE) {
            viewHolder.price.setText(Money.formatPrice(Money.PHILIPPINE_PESO, product.getProductCostUnit()));
        } else {
            viewHolder.price.setText(Money.formatPrice(Money.PHILIPPINE_PESO, product.getProductSrpUnit()));
        }

        RxView.clicks(viewHolder.itemArea).subscribe(aVoid -> {
            //increaseItemCount(viewHolder.viewCount, viewHolder);
            stateSubject.onNext(new OrderUnit(product, unit.getQuantity() + 1));
            viewHolder.count.setVisibility(View.VISIBLE);
            viewHolder.remove.setVisibility(View.VISIBLE);
        });

        RxView.clicks(viewHolder.remove).subscribe(aVoid -> {
            //removeItem(viewHolder);
            stateSubject.onNext(new OrderUnit(product, 0));
            viewHolder.count.setVisibility(View.GONE);
            viewHolder.remove.setVisibility(View.GONE);
        });

        if (unit.getQuantity() != 0) {
            viewHolder.count.setVisibility(View.VISIBLE);
            viewHolder.remove.setVisibility(View.VISIBLE);
            viewHolder.viewCount.setText(String.valueOf(unit.getQuantity()));
        } else {
            viewHolder.count.setVisibility(View.GONE);
            viewHolder.remove.setVisibility(View.GONE);
            viewHolder.viewCount.setText(String.valueOf(unit.getQuantity()));
        }

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
