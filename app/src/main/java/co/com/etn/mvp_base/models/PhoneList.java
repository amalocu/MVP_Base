package co.com.etn.mvp_base.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * com.cosmo.arquitecturamvpbase.model
 * Arquitectura-base
 * Created by alexander.vasquez on 3/10/2017.6:37 PM
 */

public class PhoneList {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("location")
    @Expose
    private Location location;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
