package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;

import co.com.etn.mvp_base.helper.ServicesFactory;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.services.IServices;
import retrofit.RetrofitError;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ProductsRepository implements IProductsRepository{

    private IServices services;

    public ProductsRepository(){
        ServicesFactory servicesFactory =new ServicesFactory();
        services = (IServices)servicesFactory.getInstance(IServices.class);
    }

    @Override
    public ArrayList<Products> getProductoList() throws RetrofitError{
        ArrayList<Products> products = services.getProduct();
        return products;
    }

    @Override
    public void createProduct(Products producto) throws RetrofitError {
        services.createProduct(producto);
    }

    @Override
    public DeleteProductResponse deleteProduct(String id) throws RetrofitError {
        return services.deleteProduct(id);
    }
}
