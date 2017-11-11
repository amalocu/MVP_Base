package co.com.etn.mvp_base.models;

import org.simpleframework.xml.Element;

/**
 * co.com.etn.mvp_base.models
 * MVP_Base
 * Created by alexander.vasquez on 7/11/2017.8:41 PM
 */

@Element(name="food")
public  class Food {

    @Element(name="name")
    private String name;
    @Element(name="price")
    private String price;
    @Element(name="description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    @Element(name="calories")
    private long calories;

}
