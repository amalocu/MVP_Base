package co.com.etn.mvp_base.views.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.models.Products;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class DetailActivity extends AppCompatActivity {


    private TextView product_detail_name;
    private TextView product_detail_description;
    private TextView product_detail_price;
    private TextView product_detail_quantity;
    private Products product;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        loadView();
        product = (Products)getIntent().getSerializableExtra(Constants.ITEM_PRODUCT);
        setDataItem();
    }

    private void setDataItem() {
        product_detail_name.setText(product.getName());
        product_detail_description.setText(product.getDescription());
        product_detail_price.setText(product.getPrice());
        product_detail_quantity.setText(product.getQuantity());

    }

    private void loadView() {
        product_detail_name = (TextView)findViewById(R.id.product_detail_name);
        product_detail_description = (TextView)findViewById(R.id.product_detail_description);
        product_detail_price = (TextView)findViewById(R.id.product_detail_price);
        product_detail_quantity = (TextView)findViewById(R.id.product_detail_quantity);


    }
}
