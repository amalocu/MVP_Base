package co.com.etn.mvp_base.views.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.CreateProductPresenter;
import co.com.etn.mvp_base.presenter.UpdateProductsPresenter;
import co.com.etn.mvp_base.views.BaseActivity;

public class UpdateActivity extends BaseActivity<UpdateProductsPresenter> implements IUpdaterProductView, TextWatcher, View.OnClickListener {

    EditText create_product_name;
    EditText create_product_description;
    EditText create_product_price;
    EditText create_product_quantity;
    Button create_product_create;
    String id;
    String name;
    String description;
    String price;
    String quantity;

    Products product;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        loadView();
        product = (Products)getIntent().getSerializableExtra(Constants.ITEM_PRODUCT);
        setDataItem();

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean res = true;
        if( isEmpty(create_product_name)&&
                isEmpty(create_product_description)&&
                isEmpty(create_product_price)&&
                isEmpty(create_product_quantity) )
            res = false;
        create_product_create.setEnabled(res);
        Log.e("ERRORRRRRRR","Resultado --------------------------"+res);

    }


    public void getProduct() {
        name=create_product_name.getText().toString();
        description=create_product_description.getText().toString();
        price=create_product_price.getText().toString();
        quantity=create_product_quantity.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.create_product_create){
            setPresenter(new UpdateProductsPresenter());
            createProgress();
            getPresenter().inject(this, getValidateItnernet());
            getPresenter().updateProducto(id, name, description, price, quantity);
        }
    }

    private void loadView() {
        create_product_name = (EditText) findViewById(R.id.create_product_name);
        create_product_description = (EditText)findViewById(R.id.create_product_description);
        create_product_price = (EditText)findViewById(R.id.create_product_price);
        create_product_quantity = (EditText)findViewById(R.id.create_product_quantity);
        create_product_create = (Button) findViewById(R.id.create_product_create);
        create_product_name.addTextChangedListener(this);
        create_product_description.addTextChangedListener(this);
        create_product_price.addTextChangedListener(this);
        create_product_quantity.addTextChangedListener(this);
        create_product_create.setOnClickListener(this);
        create_product_create.setEnabled(false);


    }

    private void setDataItem() {
        create_product_name.setText(product.getName());
        create_product_description.setText(product.getDescription());
        create_product_price.setText(product.getPrice());
        create_product_quantity.setText(product.getQuantity());
        id = product.getId();
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
}
