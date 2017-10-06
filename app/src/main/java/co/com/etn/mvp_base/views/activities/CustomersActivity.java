package co.com.etn.mvp_base.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.presenter.GetCustomerPresenter;
import co.com.etn.mvp_base.views.BaseActivity;
import co.com.etn.mvp_base.views.adapter.CustomerAdapter;

public class CustomersActivity extends BaseActivity<GetCustomerPresenter> implements IGetCustomerView {

    private ListView activity_customers_list_view;
    private CustomerAdapter customerAdapter;
    private FloatingActionButton activity_customers_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        setPresenter(new GetCustomerPresenter());
        activity_customers_list_view = (ListView)findViewById(R.id.activity_customers_list_view);
        createProgress();
        getPresenter().inject(this, getValidateItnernet());
        getPresenter().getCustomers();

        activity_customers_fab = (FloatingActionButton)findViewById(R.id.activity_customers_fab);
        activity_customers_fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(CustomersActivity.this, CreateCustomerActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override

    protected void onRestart() {
        super.onRestart();
        getPresenter().getCustomers();

    }

    @Override

    protected void onResume() {
        super.onResume();
        getPresenter().getCustomers();
    }


    @Override
    public void showCustomers(final ArrayList<Customers> customers) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callAdapter(customers);
            }
        });
    }

    private void callAdapter(ArrayList<Customers> customers) {
        customerAdapter = new CustomerAdapter(this, R.layout.layout_item_customer, customers);
        activity_customers_list_view.setAdapter(customerAdapter);
        Log.e("------", customers.toString());

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
}
