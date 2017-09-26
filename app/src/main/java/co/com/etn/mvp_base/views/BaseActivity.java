package co.com.etn.mvp_base.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import co.com.etn.mvp_base.helper.IValidateItnernet;
import co.com.etn.mvp_base.helper.ShowAlertDialog;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.presenter.BasePresenter;
import co.com.etn.mvp_base.views.activities.DetailActivity;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{

    private ProgressDialog progressDialog;
    private IValidateItnernet validateItnernet;
    private T presenter;

    public ShowAlertDialog getAlertDialog() {
        return alertDialog;
    }

    ShowAlertDialog alertDialog ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validateItnernet = new ValidateInternet(getApplicationContext());
        //setContentView(R.layout.activity_main);
        alertDialog = new ShowAlertDialog(this);
    }

    @Override
    public void showProgress(int message) {
        progressDialog.setMessage(getResources().getString(message));
        progressDialog.show();
    }

    public void createProgress(){
        this.progressDialog= new ProgressDialog(this);
    }
    @Override
    public void hideProgress() {
        progressDialog.dismiss();
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
}