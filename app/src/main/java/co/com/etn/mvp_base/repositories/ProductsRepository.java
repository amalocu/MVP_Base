package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;

import co.com.etn.mvp_base.helper.DataBase;
import co.com.etn.mvp_base.helper.ServicesFactory;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.services.IServices;
import retrofit.RetrofitError;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ProductsRepository implements IProductsRepository {

    private IServices services;

    public ProductsRepository(){
        ServicesFactory servicesFactory =new ServicesFactory();
        services = (IServices)servicesFactory.getInstance(IServices.class);
    }

    @Override
    public ArrayList<Products> getProductoList() throws RepositoryError{
       /* try {
            ArrayList<Products> products = services.getProduct();
            return products;

        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }*/
        try {
            ArrayList<Products> products = DataBase.dao.fetchAllProducts();
            return products;

        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public ArrayList<Products> getProductoListDB() throws Exception{
            ArrayList<Products> products = DataBase.dao.fetchAllProducts();
            return products;
    }



    @Override
    public void createProduct(Products producto) throws RepositoryError {
        try {
            services.createProduct(producto);

        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }


    @Override
    public DeleteProductResponse deleteProduct(String id) throws RepositoryError {
        try {
            return services.deleteProduct(id);

        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public DeleteProductResponse updateProduct(Products producto) throws RepositoryError {
        try {
            return services.updateProduct(producto.getId(),producto);

        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public boolean createProductDB(Products producto) throws RepositoryError {
        return DataBase.dao.createProduct(producto);
    }
}
