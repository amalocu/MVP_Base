package co.com.etn.mvp_base.presenter;

import android.util.Log;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.repositories.CustomerRepository;
import co.com.etn.mvp_base.repositories.ICustomerRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;

/**
 * co.com.etn.mvp_base.presenter
 * MVP_Base
 * Created by alexander.vasquez on 3/10/2017.11:19 PM
 */

public class GetCustomerPresenter extends BasePresenter<IGetCustomerView>{

    private ICustomerRepository customerRepository;

    public GetCustomerPresenter() {
        this.customerRepository=new CustomerRepository();
    }

    public GetCustomerPresenter(ICustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    public void getCustomers() {
        if(getValidateItnernet().isConnected()){
            createThread();
        }else{
            getView().showAlertDialog(R.string.validate_internet_error);
        }
    }

    public void createThread() {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getCustomersRepository();
            }
        });
        thread.start();
    }

    public void getCustomersRepository() {
        try {
            ArrayList<Customers> customers = customerRepository.getCustomers();
            if(customers.size()>0) {
                getView().showCustomers(customers);
            }else{
                getView().showAlertDialog(R.string.sin_datos);
            }
        }catch (RepositoryError e){
            e.printStackTrace();
            getView().showAlertDialog(e.getMessage());
        }finally {
            getView().hideProgress();
        }
    }
}
