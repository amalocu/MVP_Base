package co.com.etn.mvp_base.presenter;

import android.util.Log;

import org.junit.runner.RunWith;

import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.Note;
import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.models.User;
import co.com.etn.mvp_base.repositories.ICustomerRepository;
import co.com.etn.mvp_base.repositories.ILoginRepository;
import co.com.etn.mvp_base.repositories.LoginRepository;
import co.com.etn.mvp_base.repositories.NotesRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;
import co.com.etn.mvp_base.views.activities.ILoginView;

/**
 * co.com.etn.mvp_base.presenter
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.11:54 AM
 */

public class LoginPresenter extends BasePresenter<ILoginView> {


    private final ILoginRepository loginRepository;

    public LoginPresenter(){
        this.loginRepository = new LoginRepository();
    }
    public LoginPresenter(ILoginRepository repository) {
        super();
        this.loginRepository=repository;
    }

    public void login(String email, String password) {
        if(getValidateItnernet().isConnected()){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            createThread(user);
        }else{
            getView().showAlertDialog(R.string.validate_internet_error);
        }
    }

    public void autoLogin(String token) {
        if(getValidateItnernet().isConnected()){
            createThread(token);
        }else{
            getView().showAlertDialog(R.string.validate_internet_error);
        }
    }

    private void createThread(final String token) {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                autoLoginRepository(token);
            }
        });
        thread.start();
    }

    private void autoLoginRepository(String token) {
        try {
            User user = loginRepository.autoLogin(token);
            getView().showUserInfor(user);
        }catch (RepositoryError e){
            e.printStackTrace();
            getView().showAlertDialog(e.getMessage());
        }finally {
            getView().hideProgress();
        }
    }

    public void createThread(final User user) {
        getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                loginRepository(user);
            }
        });
        thread.start();
    }

    public void loginRepository(User user) {
        try {
            user = loginRepository.login(user);
            Log.d("NOTESSSSSSS","1111111111111111111111111111111");
            NotesRepository notesRepository = new NotesRepository();
            Log.d("NOTESSSSSSS","22222222222222222222222222222");
            Note note = notesRepository.getNote();
            Log.d("NOTESSSSSSS",note.getBody());
            getView().showUserInfor(user);

        }catch (RepositoryError e){
            e.printStackTrace();
            getView().showAlertDialog(e.getMessage());
        }finally {
            getView().hideProgress();
        }
    }
}
