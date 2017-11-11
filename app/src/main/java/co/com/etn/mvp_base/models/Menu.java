package co.com.etn.mvp_base.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * co.com.etn.mvp_base.models
 * MVP_Base
 * Created by alexander.vasquez on 7/11/2017.8:38 PM
 */

@Root(name="breakfast_menu")
public class Menu {

    @ElementList(inline = true)
    private ArrayList<Food> foot;
}
