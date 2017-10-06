package co.com.etn.mvp_base.presenter;

import android.util.Log;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.repositories.CustomerRepository;
import co.com.etn.mvp_base.repositories.ICustomerRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.ICreateCustomerView;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;

/**
 * co.com.etn.mvp_base.presenter
 * MVP_Base
 * Created by alexander.vasquez on 3/10/2017.11:19 PM
 */

public class CreateCustomerPresenter extends BasePresenter<ICreateCustomerView>{

    private ICustomerRepository customerRepository;

    public CreateCustomerPresenter() {
        this.customerRepository=new CustomerRepository();
    }

    public CreateCustomerPresenter(ICustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    public void createCustomers(Customers customers) {
        if(getValidateItnernet().isConnected()){
            createThread(customers);
        }else{
            getView().showAlertDialog(R.string.validate_internet_error);
        }
    }

    public void createThread(final Customers customers) {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createCustomersRepository(customers);
            }
        });
        thread.start();
    }

    public void createCustomersRepository(Customers customers) {
        try {
            Customers  customer = customerRepository.createCustomers(customers);
            if(customer==null) {
                getView().showAlertDialog(R.string.create_fallido);
            }else{
                getView().showToast(R.string.correct);

            }
        }catch (RepositoryError e){
            getView().showAlertDialog(e.getMessage());
            e.printStackTrace();
        }finally {
            getView().hideProgress();
        }
    }
}
