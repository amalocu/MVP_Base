package co.com.etn.mvp_base.models;

import android.support.v4.content.res.TypedArrayUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * com.cosmo.arquitecturamvpbase.model
 * Arquitectura-base
 * Created by alexander.vasquez on 3/10/2017.6:50 PM
 */

public class Location {
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("coordinates")
    @Expose
    private ArrayList<Double> coordinates;

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates( ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
