package co.com.etn.mvp_base.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.GetCustomerPresenter;
import co.com.etn.mvp_base.presenter.ProductsPresenter;
import co.com.etn.mvp_base.views.activities.CreateActivity;
import co.com.etn.mvp_base.views.activities.CreateCustomerActivity;
import co.com.etn.mvp_base.views.activities.CustomersActivity;
import co.com.etn.mvp_base.views.activities.DetailActivity;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;
import co.com.etn.mvp_base.views.activities.IProductView;
import co.com.etn.mvp_base.views.adapter.CustomerAdapter;

/**
 * co.com.etn.mvp_base.fragments
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.10:01 AM
 */

public class CustomesFragment extends BaseFragments<GetCustomerPresenter> implements IGetCustomerView {

    private ListView activity_customers_list_view;
    private FloatingActionButton activity_customers_fab;
    private CustomerAdapter customerAdapter;
    private SwipeRefreshLayout swipRefreshC;
    private ArrayList<Customers> customers ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.activity_customers, container, false);
        swipRefreshC = (SwipeRefreshLayout)view.findViewById(R.id.swipRefreshC);
        setPresenter(new GetCustomerPresenter());
        activity_customers_list_view = (ListView)view.findViewById(R.id.activity_customers_list_view);
        getBaseActivity().createProgress();
        getPresenter().inject(this, getValidateItnernet());
        activity_customers_fab = (FloatingActionButton)view.findViewById(R.id.activity_customers_fab);

        loadEvents( view);
        return view;
    }

    private void loadEvents(View view) {
        activity_customers_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateCustomerActivity.class);
                startActivity(intent);
            }

        });
        swipRefreshC.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getCustomers();
            }
        });
    }

    protected void onRestart() {
        getPresenter().getCustomers();

    }

    @Override
    public void onResume() {
        super.onResume();
        swipRefreshC.setRefreshing(true);
        getPresenter().getCustomers();
    }

    @Override
    public void showAlertDialog(final int id) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipRefreshC.setRefreshing(false);

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
    public void showCustomers(final ArrayList<Customers> customers) {
        this.customers=customers;
        getBaseActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                swipRefreshC.setRefreshing(false);
                callAdapter(customers);
            }
        });

    }

    private void callAdapter(final ArrayList<Customers> customers) {
        customerAdapter = new CustomerAdapter(getActivity(), R.layout.layout_item_customer, customers);
        activity_customers_list_view.setAdapter(customerAdapter);
        activity_customers_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),MapsActivity.class);
                intent.putExtra("CUSTOMER", (Customers)customers.get(position));
                startActivity(intent);
            }
        });

        Log.e("------", customers.toString());

    }


    @Override
    public void showAlertDialog(final String message) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogInterface.OnClickListener diocl = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                swipRefreshC.setRefreshing(false);

                getAlertDialog().showAlertDialog(R.string.dialog_error_title, message, false,R.string.positive_button, diocl );
            }
        });
    }
}
