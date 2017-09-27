package co.com.etn.mvp_base.views.activities;

import co.com.etn.mvp_base.views.IBaseView;

/**
 * Created by alexander.vasquez on 26/09/2017.
 */

public interface IUpdaterProductView extends IBaseView {


    void showAlertDialog(int validate_internet_error);

    void showToast(int id);

    void showAlertDialog(String message);
}
