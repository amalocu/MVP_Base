package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;

import co.com.etn.mvp_base.helper.ServicesFactory;
import co.com.etn.mvp_base.helper.TypeDecryption;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.services.IServices;
import retrofit.RetrofitError;

/**
 * ${PACKAGE_NAME}
 * MVP_Base
 * Created by alexander.vasquez on 16/09/2017.12:13 AM
 */

public class CustomerRepository implements ICustomerRepository {

    private IServices services;

    public CustomerRepository(){
        ServicesFactory servicesFactory =new ServicesFactory(TypeDecryption.JSON);
        services = (IServices)servicesFactory.getInstance(IServices.class);
    }

    @Override
    public ArrayList<Customers> getCustomers() throws RepositoryError {
        try {
            return services.getCustomers();
        } catch (RetrofitError retrofitError) {
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public Customers createCustomers(Customers customers) throws RepositoryError {
        try {
           return services.createCustomers(customers);

        }catch (RetrofitError retrofitError){
            retrofitError.printStackTrace();
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }
}
