package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import retrofit.RetrofitError;

/**
 * Created by alexander.vasquez on 23/09/2017.
 */

public interface IProductsRepository {

    public ArrayList<Products> getProductoList() throws RepositoryError;

    ArrayList<Products> getProductoListDB() throws Exception;

    public void createProduct(Products producto)  throws RepositoryError;

    public DeleteProductResponse deleteProduct(String id) throws RepositoryError;

    public DeleteProductResponse updateProduct(Products producto) throws RepositoryError;

    public boolean createProductDB(Products producto)throws RepositoryError;
}
