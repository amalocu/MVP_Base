package co.com.etn.mvp_base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.DeleteProductResponse;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.presenter.DetailProductsPresenter;
import co.com.etn.mvp_base.presenter.GetCustomerPresenter;
import co.com.etn.mvp_base.repositories.ICustomerRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;

/**
 * Created by alexander.vasquez on 23/09/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetCustomersPresenterTest {

    //Objetos a testear
    @Mock
    ValidateInternet validateInternet;
    @Mock
    ICustomerRepository customerRepository;
    @Mock
    IGetCustomerView getCustomerView;

    //Espia
    GetCustomerPresenter getCustomerPresenter;

    @InjectMocks
    Customers customers;

    //Antes de ejecutar cada
    @Before
    public void setup() throws Exception{
        //Aqui se hace la relacion entre el activity y el presentador.
        getCustomerPresenter = Mockito.spy(new GetCustomerPresenter(customerRepository));
        getCustomerPresenter.inject(getCustomerView,validateInternet);
    }

    @Test
    public void methodGetCustomersWithConnectionShouldCallMethodCreateThread(){
        Mockito.when(validateInternet.isConnected()).thenReturn(true);
        getCustomerPresenter.getCustomers();
        Mockito.verify(getCustomerPresenter).createThread();
        Mockito.verify(getCustomerView,Mockito.never()).showAlertDialog(R.string.validate_internet_error);
    }

    @Test
    public void methodGetCustomersWithConnectionShouldShowMessageError(){
        Mockito.when(validateInternet.isConnected()).thenReturn(false);
        getCustomerPresenter.getCustomers();
        Mockito.verify(getCustomerView).showAlertDialog(R.string.validate_internet_error);
        Mockito.verify(getCustomerPresenter,Mockito.never()).createThread();
    }

    @Test
    public void methodGetCustomersShouldCallMethodGetCustomersInRepositoryTrue() throws RepositoryError{
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        ArrayList<Customers> customersArrayList = new ArrayList<Customers>();
        customersArrayList.add(new Customers());
        Mockito.when(customerRepository.getCustomers()).thenReturn(customersArrayList);
        getCustomerPresenter.getCustomersRepository();
        Assert.assertTrue(customersArrayList.size()>0);
        Mockito.verify(getCustomerView).showCustomers(customersArrayList);
        Mockito.verify(getCustomerView,Mockito.never()).showAlertDialog(R.string.sin_datos);
        Mockito.verify(getCustomerView,Mockito.never()).showAlertDialog(repositoryError.getMessage());
    }

    @Test
    public void methodGetCustomersShouldCallMethodGetCustomersInRepositoryFalse() throws RepositoryError{
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        ArrayList<Customers> customersArrayList = new ArrayList<Customers>();
        Mockito.when(customerRepository.getCustomers()).thenReturn(customersArrayList);
        getCustomerPresenter.getCustomersRepository();
        Assert.assertTrue(customersArrayList.size()==0);
        Mockito.verify(getCustomerView).showAlertDialog(R.string.sin_datos);
        Mockito.verify(getCustomerView,Mockito.never()).showCustomers(customersArrayList);
        Mockito.verify(getCustomerView,Mockito.never()).showAlertDialog(repositoryError.getMessage());

    }

    @Test
    public void methodGetCustomerShouldShowAlertMethodWhenRepositoryReturnError() throws RepositoryError{
        ArrayList<Customers> customersArrayList = new ArrayList<Customers>();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        Mockito.when(customerRepository.getCustomers()).thenThrow(repositoryError);
        getCustomerPresenter.getCustomersRepository();
        Mockito.verify(getCustomerView).showAlertDialog(repositoryError.getMessage());
        Mockito.verify(getCustomerView,Mockito.never()).showCustomers(customersArrayList);
        Mockito.verify(getCustomerView,Mockito.never()).showAlertDialog(R.string.sin_datos);
    }


}
