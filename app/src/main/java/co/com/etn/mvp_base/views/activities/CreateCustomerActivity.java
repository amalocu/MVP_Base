package co.com.etn.mvp_base.views.activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.Location;
import co.com.etn.mvp_base.models.PhoneList;
import co.com.etn.mvp_base.presenter.CreateCustomerPresenter;
import co.com.etn.mvp_base.presenter.CreateProductPresenter;
import co.com.etn.mvp_base.presenter.GetCustomerPresenter;
import co.com.etn.mvp_base.views.BaseActivity;

public class CreateCustomerActivity extends BaseActivity<CreateCustomerPresenter> implements ICreateCustomerView, TextWatcher, View.OnClickListener {


    EditText activity_create_customer_edit_text_name;
    EditText activity_create_customer_edit_text_surname;
    EditText activity_create_customer_edit_text_description;
    EditText activity_create_customer_edit_text_number;
    EditText activity_create_customer_edit_text_location_x;
    EditText activity_create_customer_edit_text_location_y;
    Button   activity_create_customer_button_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
        setPresenter(new CreateCustomerPresenter());
        loadView();

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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean res = true;
        if( isEmpty(activity_create_customer_edit_text_name)&&
                isEmpty(activity_create_customer_edit_text_surname)&&
                isEmpty(activity_create_customer_edit_text_description)&&
                isEmpty(activity_create_customer_edit_text_number)&&
                isEmpty(activity_create_customer_edit_text_number)&&
                isEmpty(activity_create_customer_edit_text_location_x)&&
                isEmpty(activity_create_customer_edit_text_location_y) )
            res = false;
        activity_create_customer_button_create.setEnabled(res);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.activity_create_customer_button_create){
            Customers customer = new Customers();
            setPresenter(new CreateCustomerPresenter());
            createProgress();
            getPresenter().inject(this, getValidateItnernet());

            customer.setName(activity_create_customer_edit_text_name.getText().toString());
            customer.setSurname(activity_create_customer_edit_text_surname.getText().toString());
            customer.setPhoneList(new ArrayList<PhoneList>());
            PhoneList phone = new PhoneList();
            phone.setDescription(activity_create_customer_edit_text_description.getText().toString());
            phone.setNumber(activity_create_customer_edit_text_number.getText().toString());
            Location location = new Location();
            location.setType(getResources().getString(R.string.phone_type));
            double x = Double.parseDouble(activity_create_customer_edit_text_location_x.getText().toString());
            double y = Double.parseDouble(activity_create_customer_edit_text_location_y.getText().toString());
            location.setCoordinates(new ArrayList<Double>());
            location.getCoordinates().add(x);
            location.getCoordinates().add(y);
            phone.setLocation(location);
            customer.getPhoneList().add(phone);
            getPresenter().createCustomers(customer );
        }



    }

    private void loadView() {
        activity_create_customer_edit_text_name = (EditText) findViewById(R.id.activity_create_customer_edit_text_name);
        activity_create_customer_edit_text_surname = (EditText) findViewById(R.id.activity_create_customer_edit_text_surname);
        activity_create_customer_edit_text_description = (EditText) findViewById(R.id.activity_create_customer_edit_text_description);
        activity_create_customer_edit_text_number = (EditText) findViewById(R.id.activity_create_customer_edit_text_number);
        activity_create_customer_edit_text_location_x = (EditText) findViewById(R.id.activity_create_customer_edit_text_location_x);
        activity_create_customer_edit_text_location_y = (EditText) findViewById(R.id.activity_create_customer_edit_text_location_y);
        activity_create_customer_button_create = (Button) findViewById(R.id.activity_create_customer_button_create);
        activity_create_customer_edit_text_name.addTextChangedListener(this);
        activity_create_customer_edit_text_surname.addTextChangedListener(this);
        activity_create_customer_edit_text_description.addTextChangedListener(this);
        activity_create_customer_edit_text_number.addTextChangedListener(this);
        activity_create_customer_edit_text_location_x.addTextChangedListener(this);
        activity_create_customer_edit_text_location_y.addTextChangedListener(this);
        activity_create_customer_button_create.setOnClickListener(this);
        activity_create_customer_button_create.setEnabled(false);


    }



}
