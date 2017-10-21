package co.com.etn.mvp_base.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import co.com.etn.mvp_base.helper.IValidateItnernet;
import co.com.etn.mvp_base.helper.ShowAlertDialog;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.presenter.BasePresenter;
import co.com.etn.mvp_base.views.BaseActivity;
import co.com.etn.mvp_base.views.IBaseView;

/**
 * co.com.etn.mvp_base.fragments
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.9:33 AM
 */

public class BaseFragments <T extends BasePresenter> extends Fragment implements IBaseView {

    private BaseActivity baseActivity;
    private IValidateItnernet validateItnernet;
    private T presenter;

    public ShowAlertDialog getAlertDialog() {
        return baseActivity.getAlertDialog();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (BaseActivity)getActivity();
        validateItnernet = baseActivity.getValidateItnernet();
    }

    @Override
    public void showProgress(int message) {
       baseActivity.showProgress(message);
    }

    @Override
    public void hideProgress() {
       baseActivity.hideProgress();
    }

    public IValidateItnernet getValidateItnernet() {
        return validateItnernet;
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void setValidateItnernet(IValidateItnernet validateItnernet) {
        this.validateItnernet = validateItnernet;
    }


    public boolean isEmpty(EditText v){
        boolean res = true;
        if(v instanceof EditText){
            res =  ((EditText) v).getText().toString().isEmpty();
        }
        return res;
    }

}
