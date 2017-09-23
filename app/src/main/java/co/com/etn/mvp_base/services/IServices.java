package co.com.etn.mvp_base.services;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.models.Products;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public interface IServices {
    @GET("/products")
    public ArrayList<Products> getProduct();

    @POST("/products")
    Products createProduct( @Body Products producto);
}
