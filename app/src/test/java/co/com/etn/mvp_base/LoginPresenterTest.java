package co.com.etn.mvp_base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.helper.ValidateInternet;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.User;
import co.com.etn.mvp_base.presenter.GetCustomerPresenter;
import co.com.etn.mvp_base.presenter.LoginPresenter;
import co.com.etn.mvp_base.repositories.ILoginRepository;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.ILoginView;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;

/**
 * co.com.etn.mvp_base
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.11:55 AM
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    //Objetos a testear
    @Mock
    ValidateInternet validateInternet;
    @Mock
    ILoginRepository loginRepository;
    @Mock
    ILoginView loginView;

    //Espia
    LoginPresenter loginPresenter;

    @InjectMocks
    User user;
    String email;
    String password;

    private void setData() {
        email = "alexander@gmail.com";
        password = "123456";
        user.setName("Alexander");
        user.setUsername("alexander");
        user.set_id("");
        user.set__v(0);
        user.setEmail(email);
        user.setFollowers(0);
        user.setFollowings(0);
        user.setLikes(0);
        user.setPassword(password);
        user.setPhoto("");
        user.setToken("");

    }

    //Antes de ejecutar cada
    @Before
    public void setup() throws Exception{
        //Aqui se hace la relacion entre el activity y el presentador.
        loginPresenter = spy(new LoginPresenter(loginRepository));
        loginPresenter.inject(loginView,validateInternet);
    }

    @Test
    public void methodLoginWithConnectionShouldCallMethodCreateThread(){
        setData();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Mockito.when(validateInternet.isConnected()).thenReturn(true);
        loginPresenter.login(email,password);
        Mockito.verify(loginPresenter).createThread(Matchers.refEq(user));
        Mockito.verify(loginView, never()).showAlertDialog(R.string.validate_internet_error);
    }

    @Test
    public void methodLoginWithConnectionShouldShowMessageError(){
        setData();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Mockito.when(validateInternet.isConnected()).thenReturn(false);
        loginPresenter.login(email,password);
        Mockito.verify(loginPresenter,never()).createThread(Matchers.refEq(user));
        Mockito.verify(loginView).showAlertDialog(R.string.validate_internet_error);
    }

    @Test
    public void methodLoginShouldCallMethodLoginInRepositoryTrue() throws RepositoryError {
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        setData();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Mockito.when(loginRepository.login(user)).thenReturn(this.user);
        loginPresenter.loginRepository(user);
        Mockito.verify(loginView).showUserInfor(Matchers.refEq(this.user));
        Mockito.verify(loginView,Mockito.never()).showAlertDialog(repositoryError.getMessage());
    }


}
