package co.com.etn.mvp_base.views.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.CreateProductPresenter;
import co.com.etn.mvp_base.presenter.ProductsPresenter;
import co.com.etn.mvp_base.views.BaseActivity;
import co.com.etn.mvp_base.views.adapter.ProductAdapter;

public class CreateActivity extends BaseActivity<CreateProductPresenter> implements ICreateProductView, TextWatcher, View.OnClickListener {

    EditText create_product_name;
    EditText create_product_description;
    EditText create_product_price;
    EditText create_product_quantity;
    Button create_product_create;
    Products producto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        loadView();

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    private boolean isEmpty(EditText v){
        boolean res = true;
        if(v instanceof EditText){
           res =  ((EditText) v).getText().toString().isEmpty();
            Log.e("ERRORRRRRRR","Resultado --------------------------"+ res);
        }
        return res;
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


    @Override
    public Products getProduct() {
        Products producto = new Products();
        producto.setName(create_product_name.getText().toString());
        producto.setDescription(create_product_description.getText().toString());
        producto.setPrice(create_product_price.getText().toString());
        producto.setQuantity(create_product_quantity.getText().toString());
        return producto;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.create_product_create){
            setPresenter(new CreateProductPresenter());
            createProgress();
            getPresenter().inject(this, getValidateItnernet());
            getPresenter().validateItnernet();
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
}
