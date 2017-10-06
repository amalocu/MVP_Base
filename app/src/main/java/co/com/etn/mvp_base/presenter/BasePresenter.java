package co.com.etn.mvp_base.presenter;

import android.view.View;

import co.com.etn.mvp_base.helper.IValidateItnernet;
import co.com.etn.mvp_base.views.IBaseView;
import co.com.etn.mvp_base.views.activities.ICreateCustomerView;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class BasePresenter<T extends IBaseView> {

    private T view;
    private IValidateItnernet validateItnernet;

    public void inject(T view, IValidateItnernet validateItnernet){
        this.view=view;
        this.validateItnernet=validateItnernet;

    }

    public T getView() {
        return view;
    }

    public IValidateItnernet getValidateItnernet() {
        return validateItnernet;
    }

}
