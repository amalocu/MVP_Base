package co.com.etn.mvp_base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.presenter.DetailProductsPresenter;
import co.com.etn.mvp_base.repositories.IProductsRepository;
import co.com.etn.mvp_base.views.activities.IDetailProductView;

/**
 * Created by alexander.vasquez on 23/09/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    //Objetos a testear
    @Mock
    ValidateInternet validateInternet;
    @Mock
    IProductsRepository productsRepository;
    @Mock
    IDetailProductView detailProductView;

    //Espia
    DetailProductsPresenter detailProductsPresenter;

    //Antes de ejecutar cada
    @Before
    public void setup() throws Exception{
        //Aqui se hace la relacion entre el activity y el presentador.
        detailProductsPresenter = Mockito.spy(new DetailProductsPresenter(productsRepository));
        detailProductsPresenter.inject(detailProductView,validateInternet);
    }

    @Test
    public void methodDeleteProductWithConnectionShouldCallMethodCreateThreadDeleteProducto(){
        String id = "13gljhhdd232";

        Mockito.when(validateInternet.isConnected()).thenReturn(true);
        detailProductsPresenter.deleteProducto(id);
        Mockito.verify(detailProductsPresenter).createThread(id);
        Mockito.verify(detailProductView,Mockito.never()).showAlertDialog(R.string.validate_internet_error);

    }

    @Test
    public void methodDeleteProductWithConnectionShouldShowMessageError(){
        String id = "13gljhhdd232";
        Mockito.when(validateInternet.isConnected()).thenReturn(false);
        detailProductsPresenter.deleteProducto(id);
        Mockito.verify(detailProductView).showAlertDialog(R.string.validate_internet_error);
        Mockito.verify(detailProductsPresenter, Mockito.never()).createThread(id);
    }

    @Test
    public void methodDeleteProductShouldCallMethodDeletoProductInRepositoryTrue(){
        String id = "13gljhhdd232";
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setStatus(true);
        Mockito.when(productsRepository.deleteProduct(id)).thenReturn(deleteProductResponse);
        detailProductsPresenter.deleteProductRepository(id);
        Assert.assertTrue(deleteProductResponse.isStatus());
        Mockito.verify(detailProductView).showToast(R.string.borrado_exitoso);
        Mockito.verify(detailProductView,Mockito.never()).showAlertDialog(R.string.borrado_fallido);

    }

    @Test
    public void methodDeleteProductShouldCallMethodDeletoProductInRepositoryFalse(){
        String id = "13gljhhdd232";
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setStatus(false);
        Mockito.when(productsRepository.deleteProduct(id)).thenReturn(deleteProductResponse);
        detailProductsPresenter.deleteProductRepository(id);
        Assert.assertFalse(deleteProductResponse.isStatus());
        Mockito.verify(detailProductView).showAlertDialog(R.string.borrado_fallido);
        Mockito.verify(detailProductView,Mockito.never()).showToast(R.string.borrado_exitoso);


    }


}
