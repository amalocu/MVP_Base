package co.com.etn.mvp_base.presenter;

import android.util.Log;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.repositories.IProductsRepository;
import co.com.etn.mvp_base.repositories.ProductsRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.ICreateProductView;
import co.com.etn.mvp_base.views.activities.IProductView;
import retrofit.RetrofitError;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class CreateProductPresenter extends BasePresenter<ICreateProductView> {
    private IProductsRepository productsRepository;

    public CreateProductPresenter(){
        this.productsRepository=new ProductsRepository();
    }
    public CreateProductPresenter(IProductsRepository repository){
        this.productsRepository=repository;
    }
    public void validateItnernet() {
        if(getValidateItnernet().isConnected()){
            createThread();
        }else{
            Log.getStackTraceString(new Throwable("11111"));
        }
    }

    private void createThread() {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createProduct();
            }
        });
        thread.start();

    }

    private void createProduct() {
        try {
            Products producto =  getView().getProduct();
            productsRepository.createProduct(producto);
        }catch (RepositoryError e){
           e.printStackTrace();
        }finally {
            getView().hideProgress();
        }

    }

}
