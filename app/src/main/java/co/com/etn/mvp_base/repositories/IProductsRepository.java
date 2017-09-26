package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import retrofit.RetrofitError;

/**
 * Created by alexander.vasquez on 23/09/2017.
 */

public interface IProductsRepository {

    public ArrayList<Products> getProductoList() throws RetrofitError;

    public void createProduct(Products producto)  throws RetrofitError;

    public DeleteProductResponse deleteProduct(String id) throws RetrofitError;
}
