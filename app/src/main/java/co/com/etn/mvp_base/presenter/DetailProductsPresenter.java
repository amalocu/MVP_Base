package co.com.etn.mvp_base.presenter;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.repositories.IProductsRepository;
import co.com.etn.mvp_base.repositories.ProductsRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IDetailProductView;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class DetailProductsPresenter extends BasePresenter<IDetailProductView> {
    private IProductsRepository productsRepository;

    public DetailProductsPresenter(){
        this.productsRepository=new ProductsRepository();
    }
    public DetailProductsPresenter(IProductsRepository repository){
        this.productsRepository=repository;
    }
    public void deleteProducto(String id) {
        if(getValidateItnernet().isConnected()){
            createThread(id);
        }else{
            getView().showAlertDialog(R.string.validate_internet_error);
        }
    }

    public void createThread(final String id) {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                deleteProductRepository(id);
            }
        });
        thread.start();

    }



    public void deleteProductRepository(String id) {
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        try {
            deleteProductResponse = productsRepository.deleteProduct(id);
            if(deleteProductResponse.isStatus()){
                getView().showToast(R.string.borrado_exitoso);
            }
            else{
                getView().showAlertDialog(R.string.borrado_fallido);
            }
        }catch (RepositoryError e){
            e.printStackTrace();
            getView().showAlertDialog(e.getMessage());

        }finally {
            getView().hideProgress();
        }

    }


}
