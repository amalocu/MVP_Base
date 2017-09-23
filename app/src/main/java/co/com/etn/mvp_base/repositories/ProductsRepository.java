package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.helper.ServicesFactory;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.services.IServices;
import retrofit.RetrofitError;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ProductsRepository {

    private IServices services;

    public ProductsRepository(){
        ServicesFactory servicesFactory =new ServicesFactory();
        services = (IServices)servicesFactory.getInstance(IServices.class);
    }
    public ArrayList<Products> getProductoList() throws RetrofitError{
        ArrayList<Products> products = services.getProduct();
        return products;
    }

    public void createProduct(Products producto) {
        services.createProduct(producto);
    }
}
