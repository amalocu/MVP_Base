package co.com.etn.mvp_base.presenter;

import android.content.Context;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.CustomSharePreference;
import co.com.etn.mvp_base.models.User;
import co.com.etn.mvp_base.repositories.RepositoryError;
import co.com.etn.mvp_base.views.activities.IUserView;

/**
 * co.com.etn.mvp_base.presenter
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.11:54 AM
 */

public class UserPresenter extends BasePresenter<IUserView> {

    //private final IUserRepository userRepository;

    public UserPresenter(){
        //this.userRepository = new UserRepository();
    }

    public void getUser() {
            getView().showUserInfo();
        getView().getClass();
    }


}
