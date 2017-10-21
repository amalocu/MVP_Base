package co.com.etn.mvp_base.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.ProductsPresenter;
import co.com.etn.mvp_base.views.activities.CreateActivity;
import co.com.etn.mvp_base.views.activities.DetailActivity;
import co.com.etn.mvp_base.views.activities.IProductView;
import co.com.etn.mvp_base.views.activities.ProductActivity;
import co.com.etn.mvp_base.views.adapter.ProductAdapter;

/**
 * co.com.etn.mvp_base.fragments
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.9:32 AM
 */

public class ProductFragment extends BaseFragments<ProductsPresenter> implements IProductView {

    private ListView activity_product_list_view;
    private FloatingActionButton activity_product_fab;
    private ProductAdapter productAdapter;
    private SwipeRefreshLayout swipRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.activity_produts, container, false);
        swipRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipRefresh);
        setPresenter(new ProductsPresenter());
        activity_product_list_view = (ListView)view.findViewById(R.id.activity_product_list_view);
        activity_product_list_view.setNestedScrollingEnabled(true);
        getBaseActivity().createProgress();

        getPresenter().inject(this, getValidateItnernet());
        activity_product_fab = (FloatingActionButton)view.findViewById(R.id.activity_products_fab);

        loadEvents(view);

        return  view;
    }

    private void loadEvents(View view) {
        activity_product_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateActivity.class);
                startActivity(intent);
            }

        });
        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getValidateItnernet();
            }
        });
    }

    @Override
    public void showProductsList(final ArrayList<Products> productos) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipRefresh.setRefreshing(false);
                callAdapter(productos);
            }
        });
    }


    private void callAdapter(final ArrayList<Products> productos) {
        productAdapter = new ProductAdapter(getActivity(), R.layout.product_layout_item, productos);
        activity_product_list_view.setAdapter(productAdapter);
        activity_product_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(Constants.ITEM_PRODUCT, (Products)productos.get(position));
                startActivity(intent);
            }
        });
    }


    protected void onRestart() {
          getPresenter().validateItnernet();
    }

    @Override
    public void onResume() {
        super.onResume();
        swipRefresh.setRefreshing(true);
        getPresenter().validateItnernet();
    }

}
