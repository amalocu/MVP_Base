package co.com.etn.mvp_base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.DetailProductsPresenter;
import co.com.etn.mvp_base.presenter.UpdateProductsPresenter;
import co.com.etn.mvp_base.repositories.IProductsRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IDetailProductView;
import co.com.etn.mvp_base.views.activities.IUpdaterProductView;

/**
 * Created by alexander.vasquez on 23/09/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductPresenterTest {

    //Objetos a testear
    @Mock
    ValidateInternet validateInternet;
    @Mock
    IProductsRepository productsRepository;
    @Mock
    IUpdaterProductView updateProductView;

    //Espia
    UpdateProductsPresenter updateProductsPresenter;

    Products product;
    public void setProduct() {
        this.product = new Products();
        product.setId("13gljhhdd232");
        product.setName("La Sabrosa");
        product.setDescription("La Sabrosa y riquisima");
        product.setPrice("1000");
        product.setQuantity("10");

    }

    //Antes de ejecutar cada
    @Before
    public void setup() throws Exception{
        //Aqui se hace la relacion entre el activity y el presentador.
        updateProductsPresenter = Mockito.spy(new UpdateProductsPresenter(productsRepository));
        updateProductsPresenter.inject(updateProductView,validateInternet);
        setProduct();
    }

    @Test
    public void methodUpdateProductWithConnectionShouldCallMethodCreateThreadUpdateProducto(){
        setProduct();
        Mockito.when(validateInternet.isConnected()).thenReturn(true);
        updateProductsPresenter.updateProducto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity() );
        Mockito.verify(updateProductsPresenter).createThread(product);
        Mockito.verify(updateProductView,Mockito.never()).showAlertDialog(R.string.validate_internet_error);

    }

    @Test
    public void methodUpdateProductWithConnectionShouldShowMessageError(){
        setProduct();
        Mockito.when(validateInternet.isConnected()).thenReturn(false);
        updateProductsPresenter.updateProducto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity() );
        Mockito.verify(updateProductView).showAlertDialog(R.string.validate_internet_error);
        Mockito.verify(updateProductsPresenter, Mockito.never()).createThread(product );
    }


    @Test
    public void methodUpdateProductShouldCallMethodUpdateProductInRepositoryTrue() throws RepositoryError{
        setProduct();
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setStatus(true);
        Mockito.when(productsRepository.updateProduct( product)).thenReturn(deleteProductResponse);
        updateProductsPresenter.udpateProductRepository(product);
        Assert.assertTrue(deleteProductResponse.isStatus());
        Mockito.verify(updateProductView).showToast(R.string.update_exitoso);
        Mockito.verify(updateProductView,Mockito.never()).showAlertDialog(R.string.update_fallido);

    }

    @Test
    public void methodDeleteProductShouldCallMethodDeletoProductInRepositoryFalse() throws RepositoryError{
        setProduct();
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setStatus(false);
        Mockito.when(productsRepository.updateProduct(product)).thenReturn(deleteProductResponse);
        updateProductsPresenter.udpateProductRepository(product);
        Assert.assertFalse(deleteProductResponse.isStatus());
        Mockito.verify(updateProductView).showAlertDialog(R.string.update_fallido);
        Mockito.verify(updateProductView,Mockito.never()).showToast(R.string.update_exitoso);


    }


    @Test
    public void methodDeleteProductShouldShowAlertMethodWhenRepositoryReturnError() throws RepositoryError{
        setProduct();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        Mockito.when(productsRepository.updateProduct(product)).thenThrow(repositoryError);
        updateProductsPresenter.udpateProductRepository(product);
        Mockito.verify(updateProductView).showAlertDialog(repositoryError.getMessage());
        Mockito.verify(updateProductView,Mockito.never()).showToast(R.string.correct);

    }


}
