package com.mybareskinph.theBareskinApp.home.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybareskinph.theBareskinApp.R;
import com.mybareskinph.theBareskinApp.home.adapters.ProductAdapter;
import com.mybareskinph.theBareskinApp.home.implementations.ProductOrderPresenterImpl;
import com.mybareskinph.theBareskinApp.home.pojos.OrderUnit;
import com.mybareskinph.theBareskinApp.home.pojos.Product;
import com.mybareskinph.theBareskinApp.home.viewInterfaces.ProductOrderView;
import com.mybareskinph.theBareskinApp.widgets.Tags;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.subjects.PublishSubject;

public class ProductOrdersFragment extends FormFragments implements ProductOrderView {

    public static final int NEW_ORDER_MODE = 0;
    public static final int REGISTER_SALE_MODE = 1;
    public static final String MODE = "mode";

    @BindView(R.id.tv_page_subtext)
    TextView subtext;

    @BindView(R.id.et_product_name)
    AutoCompleteTextView productName;

    @BindView(R.id.product_view)
    RecyclerView productView;

    @BindView(R.id.ll_tags_view)
    LinearLayout tagsView;

    @BindView(R.id.ll_products)
    LinearLayout productsView;

    @BindView(R.id.ll_loading)
    LinearLayout loading;

    ProductOrderPresenterImpl presenter;
    ProductAdapter adapter;

    private final PublishSubject<OrderUnit> itemsSubject = PublishSubject.create();
    private final PublishSubject<Integer> scrollSubject = PublishSubject.create();
    private final PublishSubject<String> tagsSubject = PublishSubject.create();

    private final PublishSubject<List<OrderUnit>> orderWatcher = PublishSubject.create();

    public ProductOrdersFragment() {
    }


    public static ProductOrdersFragment newInstance(int mode) {
        ProductOrdersFragment fragment = new ProductOrdersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MODE, mode);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_order, container, false);
        bindView(this, view);

        int mode = getArguments().getInt(MODE);

        if(mode == ProductOrdersFragment.REGISTER_SALE_MODE) {
            subtext.setText("Choose the products bought by the buyer");
        }

        tagsSubject
                .asObservable()
                .compose(bind())
                .subscribe(tag -> {
                    adapter.showProductTaggedAs(tag, presenter.getOrder());
                    presenter.setCurrentTag(tag);
                });

        itemsSubject
                .asObservable()
                .compose(bind())
                .subscribe(orderUnit -> {
                    presenter.updateOrder(orderUnit, scrollSubject);
                    orderWatcher.onNext(presenter.getOrder());
                    adapter.showProductTaggedAs(presenter.currentTag, presenter.getOrder());
                });

        scrollSubject
                .asObservable()
                .compose(bind())
                .subscribe(pos -> {
                    switch (pos) {
                        case ProductOrderPresenterImpl.ADD:
                            productView.scrollToPosition(presenter.getOrder().size() - 1);
                            break;
                        case ProductOrderPresenterImpl.REMOVE:
                        default:
                            productView.scrollToPosition(pos);
                    }
                });

        presenter = new ProductOrderPresenterImpl(this, getRetrofit());
        presenter.loadProducts();

        return view;
    }

    public Observable<List<OrderUnit>> orderListWatcher() {
        return orderWatcher;
    }

    @Override
    public void fillRecyclerView(List<Product> productList) {
        int mode = getArguments().getInt(MODE);
        adapter = new ProductAdapter(getContext(), productList, itemsSubject, mode);
        productView.setAdapter(adapter);
        productView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void fillTagsView(List<String> tags) {
        for (String tag : tags) {
            Tags t = new Tags(getContext(), tag, tagsSubject);
            tagsView.addView(t);
        }
    }

    @Override
    public void enableLoading(boolean condition) {
        loading.setVisibility(condition ? View.VISIBLE : View.GONE);
        productsView.setVisibility(condition ? View.GONE : View.VISIBLE);
    }

}
