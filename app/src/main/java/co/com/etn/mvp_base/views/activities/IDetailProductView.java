package co.com.etn.mvp_base.views.activities;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.views.IBaseView;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public interface IDetailProductView extends IBaseView{

    public void showAlertDialog(int id);

    public void showToast(int id);

    public void showToast(String message);

    public void showAlertDialog(String message);
}
