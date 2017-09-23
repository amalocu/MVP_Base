package co.com.etn.mvp_base.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Products;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ProductAdapter extends ArrayAdapter<Products> {

    private ArrayList<Products> productos;
    private Activity contexto;
    private int resource;
    private Products product;
    private TextView name;
    private TextView price;

    public ProductAdapter(Activity context,  int resource, @NonNull ArrayList<Products> productos) {
        super(context, resource, productos);
        this.contexto=context;
        this.productos= productos;
        this.resource=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout_item,parent,false);
        this.product=this.productos.get(position);
        setDataItem(convertView);
        name.setText(product.getName());
        price.setText(product.getPrice());
        return convertView;
    }

    private void setDataItem(View convertView) {
        name = (TextView)convertView.findViewById(R.id.product_item_name);
        price = (TextView)convertView.findViewById(R.id.product_item_price);
    }
}
