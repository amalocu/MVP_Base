package co.com.etn.mvp_base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.Location;
import co.com.etn.mvp_base.models.PhoneList;
import co.com.etn.mvp_base.presenter.CreateCustomerPresenter;
import co.com.etn.mvp_base.repositories.ICustomerRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.ICreateCustomerView;


/**
 * Created by alexander.vasquez on 23/09/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomersPresenterTest {

    //Objetos a testear
    @Mock
    ValidateInternet validateInternet;
    @Mock
    ICustomerRepository customerRepository;
    @Mock
    ICreateCustomerView createCustomerView;

    //Espia
    CreateCustomerPresenter createCustomerPresenter;

    @InjectMocks
    Customers customers;
    @InjectMocks
    PhoneList phoneList;
    @InjectMocks
    Location location;

    private void setClient() {
        customers.setPhoneList(new ArrayList<PhoneList>());
        location.setType("point");
        location.setCoordinates(new ArrayList<Double>());
        location.getCoordinates().add(-76.0);
        location.getCoordinates().add(-76.0);
        phoneList.setNumber("1");
        phoneList.setDescription("House");
        phoneList.setLocation(location);
        customers.getPhoneList().add(phoneList);

        location.setType("point");
        location.setCoordinates(new ArrayList<Double>());
        location.getCoordinates().add(-76.0);
        location.getCoordinates().add(-76.0);
        phoneList.setNumber("2");
        phoneList.setDescription("Office");
        phoneList.setLocation(location);
        customers.getPhoneList().add(phoneList);

        customers.setName("Alexander");
        customers.setSurname("Vasquez");
    }

    //Antes de ejecutar cada
    @Before
    public void setup() throws Exception{
        //Aqui se hace la relacion entre el activity y el presentador.
        createCustomerPresenter = Mockito.spy(new CreateCustomerPresenter(customerRepository));
        createCustomerPresenter.inject(createCustomerView,validateInternet);
        setClient();
    }

    @Test
    public void methodCreateCustomerWithConnectionShouldCallMethodCreateThreadCreateCustomero(){
        setClient();
        Mockito.when(validateInternet.isConnected()).thenReturn(true);
        createCustomerPresenter.createCustomers(customers );
        Mockito.verify(createCustomerPresenter).createThread(Matchers.refEq(customers));
        Mockito.verify(createCustomerView,Mockito.never()).showAlertDialog(R.string.validate_internet_error);

    }

    @Test
    public void methodCreateCustomerWithConnectionShouldShowMessageError(){
        setClient();
        Mockito.when(validateInternet.isConnected()).thenReturn(false);
        createCustomerPresenter.createCustomers(customers );
        Mockito.verify(createCustomerView).showAlertDialog(R.string.validate_internet_error);
        Mockito.verify(createCustomerPresenter, Mockito.never()).createThread(customers );
    }


    @Test
    public void methodCreateCustomerShouldCallMethodCreateCustomerInRepositoryTrue() throws RepositoryError{
        setClient();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        Mockito.when(customerRepository.createCustomers( customers)).thenReturn(customers);
        createCustomerPresenter.createCustomersRepository(customers);
        Assert.assertNotNull(customers);
        Mockito.verify(createCustomerView).showToast(R.string.correct);
        Mockito.verify(createCustomerView,Mockito.never()).showAlertDialog(R.string.create_fallido);
        Mockito.verify(createCustomerView,Mockito.never()).showAlertDialog(repositoryError.getIdError());
    }

    @Test
    public void methodCreateCustomerShouldCallMethodCreateCustomerInRepositoryFalse() throws RepositoryError{
        setClient();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        Customers customer = null;
        Mockito.when(customerRepository.createCustomers(customers)).thenReturn(customer);
        createCustomerPresenter.createCustomersRepository(customers);
        Assert.assertNull(customer);
        Mockito.verify(createCustomerView).showAlertDialog(R.string.create_fallido);
        Mockito.verify(createCustomerView,Mockito.never()).showToast(R.string.correct);
        Mockito.verify(createCustomerView,Mockito.never()).showAlertDialog(repositoryError.getIdError());
    }


    @Test
    public void methodCreateCustomerShouldShowAlertMethodWhenRepositoryReturnError() throws RepositoryError{
        setClient();
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        Mockito.when(customerRepository.createCustomers(customers)).thenThrow(repositoryError);
        createCustomerPresenter.createCustomersRepository(customers);
        Mockito.verify(createCustomerView).showAlertDialog(repositoryError.getMessage());
        Mockito.verify(createCustomerView,Mockito.never()).showToast(R.string.correct);
        Mockito.verify(createCustomerView,Mockito.never()).showAlertDialog(R.string.create_fallido);


    }


}
