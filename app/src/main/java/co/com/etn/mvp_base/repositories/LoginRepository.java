package co.com.etn.mvp_base.repositories;

import co.com.etn.mvp_base.helper.ServicesFactory;
import co.com.etn.mvp_base.models.User;
import co.com.etn.mvp_base.services.IServices;
import retrofit.RetrofitError;

/**
 * co.com.etn.mvp_base.repositories
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.12:13 PM
 */

public class LoginRepository implements ILoginRepository {

    private IServices services;

    public LoginRepository(){
        ServicesFactory servicesFactory =new ServicesFactory();
        services = (IServices)servicesFactory.getInstance(IServices.class);
    }

    @Override
    public User login(User user) throws RepositoryError {
        try {
            return services.login( user.getEmail(), user.getPassword() );
        } catch (RetrofitError retrofitError) {
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public User autoLogin(String token) throws RepositoryError {
        try {
            return services.autoLogin( "bearer:"+token );
        } catch (RetrofitError retrofitError) {
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }


}
