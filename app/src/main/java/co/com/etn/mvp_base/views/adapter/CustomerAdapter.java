package co.com.etn.mvp_base.views.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Customers;

/**
 * ${PACKAGE_NAME}
 * MVP_Base
 * Created by alexander.vasquez on 16/09/2017.12:40 AM
 */

public class CustomerAdapter extends ArrayAdapter<Customers> {

    private ArrayList<Customers> customers;
    private Activity context;
    private int resource;
    private Customers customer;
    private TextView name;
    private TextView surname;

    public CustomerAdapter(Activity context, int resource, @NonNull ArrayList<Customers> customers) {
        super(context, resource, customers);
        this.context=context;
        this.customers= customers;
        this.resource=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_customer,parent,false);
        this.customer=this.customers.get(position);
        setDataItem(convertView);
        name.setText(customer.getName());
        surname.setText(customer.getSurname());
        return convertView;
    }

    private void setDataItem(View convertView) {
        name = (TextView)convertView.findViewById(R.id.layout_item_customer_name);
        surname = (TextView)convertView.findViewById(R.id.layout_item_customer_surname);
    }
}
