package co.com.etn.mvp_base.repositories;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.Customers;

/**
 * co.com.etn.mvp_base.repositories
 * MVP_Base
 * Created by alexander.vasquez on 3/10/2017.11:17 PM
 */

public interface ICustomerRepository {

    public ArrayList<Customers> getCustomers() throws RepositoryError;

    public Customers createCustomers(Customers customers)  throws RepositoryError;
}
