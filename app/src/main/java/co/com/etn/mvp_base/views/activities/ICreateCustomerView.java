package co.com.etn.mvp_base.views.activities;

import co.com.etn.mvp_base.views.IBaseView;

/**
 * co.com.etn.mvp_base.views.activities
 * MVP_Base
 * Created by alexander.vasquez on 4/10/2017.1:18 AM
 */

public interface ICreateCustomerView extends IBaseView {
    public void showAlertDialog(int id);

    public void showAlertDialog(String message);


    public void showToast(int correct);
}
