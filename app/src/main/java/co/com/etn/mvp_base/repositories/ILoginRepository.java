package co.com.etn.mvp_base.repositories;

import co.com.etn.mvp_base.models.User;

/**
 * co.com.etn.mvp_base.repositories
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.12:07 PM
 */

public interface ILoginRepository {

    public User login(User user)throws RepositoryError;

    public User autoLogin(String token)throws RepositoryError;
}
