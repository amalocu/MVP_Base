package co.com.etn.mvp_base.repositories;

import co.com.etn.mvp_base.models.Menu;

/**
 * co.com.etn.mvp_base.repositories
 * MVP_Base
 * Created by alexander.vasquez on 7/11/2017.8:45 PM
 */

public interface IMenuRepository {

    public Menu getMenu()  throws RepositoryError;
}
