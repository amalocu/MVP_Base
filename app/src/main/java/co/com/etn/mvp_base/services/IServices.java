package co.com.etn.mvp_base.services;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Menu;
import co.com.etn.mvp_base.models.Note;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.models.User;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public interface IServices {

    @GET("/products")
    public ArrayList<Products> getProduct();

    @POST("/products")
    public Products createProduct( @Body Products producto);

    @DELETE("/products/{id}")
    public DeleteProductResponse deleteProduct(@Path("id") String id);

    @PUT("/products/{id}")
    public DeleteProductResponse updateProduct(@Path("id") String id, @Body Products producto);

    @GET("/customers")
    public ArrayList<Customers> getCustomers();

    @POST("/customers")
    Customers createCustomers(@Body  Customers customers);

    @GET("/user/auth")
    public User login(@Query("email")String email, @Query("password") String password);

    @GET("/user")
    User autoLogin(@Header("Authorization") String token);

    @GET("/note.xml")
    Note getNote();

    @GET("/simple.xml")
    Menu getMenu();
}
