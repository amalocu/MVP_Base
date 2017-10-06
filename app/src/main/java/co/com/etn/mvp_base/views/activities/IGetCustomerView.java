package co.com.etn.mvp_base.views.activities;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.views.IBaseView;

/**
 * co.com.etn.mvp_base.views.activities
 * MVP_Base
 * Created by alexander.vasquez on 3/10/2017.11:18 PM
 */

public interface IGetCustomerView extends IBaseView {
    public void showAlertDialog(int id);
    public void showCustomers(ArrayList<Customers> customers);
    public void showAlertDialog(String message);
}
