package co.com.etn.mvp_base.presenter;

import android.util.Log;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.repositories.IProductsRepository;
import co.com.etn.mvp_base.repositories.ProductsRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IProductView;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ProductsPresenter extends BasePresenter<IProductView> {
    private IProductsRepository productsRepository;

    public ProductsPresenter(){
        this.productsRepository=new ProductsRepository();
    }
    public ProductsPresenter(IProductsRepository repository){
        this.productsRepository=repository;
    }
    public void validateItnernet() {
        if(getValidateItnernet().isConnected()){
            createThread();
        }else{
            Log.getStackTraceString(new Throwable("11111"));
        }
        createThread();
    }

    private void createThread() {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //getProductList();
                getProductListLocal();
            }
        });
        thread.start();

    }

    private void getProductList() {
        try {
            ArrayList<Products> productos = productsRepository.getProductoList();
            getView().showProductsList(productos);
        }catch (RepositoryError e){
           e.printStackTrace();
        }finally {
            getView().hideProgress();
        }

    }

    private void getProductListLocal() {
        try {
            ArrayList<Products> productos = productsRepository.getProductoListDB();
            Log.e("ERRORORORORORORORORORO","Productos Leidos------------------------------------------>"+String.valueOf(productos.size()));
            getView().showProductsList(productos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            getView().hideProgress();
        }

    }


}
