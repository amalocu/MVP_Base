package co.com.etn.mvp_base.views.activities;

import co.com.etn.mvp_base.models.User;
import co.com.etn.mvp_base.views.IBaseView;

/**
 * co.com.etn.mvp_base.views.activities
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.12:09 PM
 */

public interface ILoginView extends IBaseView {

    public  void showUserInfor(User user);
    public void showAlertDialog(final int validate_internet_error);
    public void showAlertDialog(String message);

}
