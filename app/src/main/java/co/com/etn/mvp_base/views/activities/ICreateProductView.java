package co.com.etn.mvp_base.views.activities;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.views.IBaseView;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public interface ICreateProductView extends IBaseView{

    public Products getProduct();
}
