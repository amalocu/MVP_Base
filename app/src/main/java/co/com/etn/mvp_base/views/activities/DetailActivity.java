package co.com.etn.mvp_base.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.helper.ShowAlertDialog;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.DetailProductsPresenter;
import co.com.etn.mvp_base.presenter.ProductsPresenter;
import co.com.etn.mvp_base.repositories.ProductsRepository;
import co.com.etn.mvp_base.views.BaseActivity;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class DetailActivity  extends BaseActivity<DetailProductsPresenter> implements IDetailProductView {


    private TextView product_detail_name;
    private TextView product_detail_description;
    private TextView product_detail_price;
    private TextView product_detail_quantity;
    private Products product;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        createProgress();
        setPresenter( new DetailProductsPresenter(new ProductsRepository()));
        getPresenter().inject(this, getValidateItnernet());
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

    @Override
    public void showAlertDialog(final int id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogInterface.OnClickListener diocl = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                getAlertDialog().showAlertDialog(R.string.dialog_error_title, id, false,R.string.positive_button, diocl );
            }
        });
    }

    @Override
    public void showToast(final int id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), getResources().getString(id), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showAlertDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogInterface.OnClickListener diocl = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                getAlertDialog().showAlertDialog(R.string.dialog_error_title, message, false,R.string.positive_button, diocl );
            }
        });
    }

    public void onClick(View view){
        getPresenter().deleteProducto(product.getId());
    }
}
