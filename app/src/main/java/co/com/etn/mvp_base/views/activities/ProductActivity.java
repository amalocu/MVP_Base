package co.com.etn.mvp_base.views.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.ProductsPresenter;
import co.com.etn.mvp_base.views.BaseActivity;
import co.com.etn.mvp_base.views.adapter.ProductAdapter;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ProductActivity  extends BaseActivity<ProductsPresenter> implements IProductView{

    private ListView activity_product_list_view;
    private ProductAdapter productAdapter;
    private FloatingActionButton activity_product_fab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produts);
        setPresenter(new ProductsPresenter());
        activity_product_list_view = (ListView)findViewById(R.id.activity_product_list_view);
        createProgress();
        getPresenter().inject(this, getValidateItnernet());
        getPresenter().validateItnernet();

        activity_product_fab = (FloatingActionButton)findViewById(R.id.activity_products_fab);
        activity_product_fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CreateActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override

    protected void onRestart() {

        super.onRestart();

        getPresenter().validateItnernet();

    }

    @Override

    protected void onResume() {

        super.onResume();

        //progress.show();

        getPresenter().validateItnernet();

    }

    @Override
    public void showProductsList(final ArrayList<Products> productos) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callAdapter(productos);
            }
        });
    }

    private void callAdapter(final ArrayList<Products> productos) {
        productAdapter = new ProductAdapter(this, R.layout.product_layout_item, productos);
        activity_product_list_view.setAdapter(productAdapter);
        activity_product_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductActivity.this,DetailActivity.class);
                intent.putExtra(Constants.ITEM_PRODUCT, (Products)productos.get(position));
                startActivity(intent);
            }
        });
    }
}
