package co.com.etn.mvp_base.services;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public interface IServices {
    @GET("/products")
    public ArrayList<Products> getProduct();

    @POST("/products")
    Products createProduct( @Body Products producto);

    @DELETE("/products/{id}")
    DeleteProductResponse deleteProduct(@Path("id") String id);

    @PUT("/products/{id}")
    DeleteProductResponse updateProduct(@Path("id") String id, @Body Products producto);
}
