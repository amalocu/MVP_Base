package co.com.etn.mvp_base.dao;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.Products;

/**
 * co.com.etn.mvp_base.dao
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.9:42 AM
 */

public interface IProductDao {

        public ArrayList<Products> fetchAllProducts();
        public boolean createProduct(Products product);

}
