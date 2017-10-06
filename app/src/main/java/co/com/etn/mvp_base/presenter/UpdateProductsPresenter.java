package co.com.etn.mvp_base.presenter;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.repositories.IProductsRepository;
import co.com.etn.mvp_base.repositories.ProductsRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IUpdaterProductView;

/**
 * Created by alexander.vasquez on 26/09/2017.
 */

public class UpdateProductsPresenter extends BasePresenter<IUpdaterProductView>  {
    private IProductsRepository productsRepository;

    public UpdateProductsPresenter(){
        this.productsRepository=new ProductsRepository();
    }
    public UpdateProductsPresenter(IProductsRepository repository){
        this.productsRepository=repository;
    }


    public void updateProducto(String id, String name, String description, String price, String quantity ) {
        if(getValidateItnernet().isConnected()){
            Products producto = new Products();
            producto.setName(name);
            producto.setDescription(description);
            producto.setQuantity(quantity);
            producto.setPrice(price);
            producto.setId(id);
            createThread(producto);
        }else{
            getView().showAlertDialog(R.string.validate_internet_error);
        }
    }

    public void createThread(final Products product) {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                udpateProductRepository(product);
            }
        });
        thread.start();
    }

    public void udpateProductRepository(Products product) {
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        try {
            deleteProductResponse = productsRepository.updateProduct(product);
            if(deleteProductResponse!=null && deleteProductResponse.isStatus()){
                getView().showToast(R.string.update_exitoso);
            }
            else{
                getView().showAlertDialog(R.string.update_fallido);
            }
        }catch (RepositoryError e){
            e.printStackTrace();
            getView().showAlertDialog(e.getMessage());

        }finally {
            getView().hideProgress();
        }

    }
}
