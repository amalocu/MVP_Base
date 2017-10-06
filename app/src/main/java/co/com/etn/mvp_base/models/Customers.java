package co.com.etn.mvp_base.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * com.cosmo.arquitecturamvpbase.model
 * Arquitectura-base
 * Created by alexander.vasquez on 3/10/2017.6:35 PM
 */

public class Customers {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("phoneList")
    @Expose
    private ArrayList<PhoneList> phoneList;


    public ArrayList<PhoneList> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<PhoneList> phoneList) {
        this.phoneList = phoneList;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
