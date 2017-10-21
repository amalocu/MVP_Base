package co.com.etn.mvp_base.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import com.google.gson.Gson;

import co.com.etn.mvp_base.models.User;

/**
 * co.com.etn.mvp_base.helper
 * MVP_Base
 *
 * Created by alexander.vasquez on 17/10/2017.6:29 PM
 */

public class CustomSharePreference {

    private SharedPreferences sharedPreferences;

    public CustomSharePreference (Context context){
        this.sharedPreferences=context.getSharedPreferences(Constants.PREFERENS_FILE, Context.MODE_PRIVATE);
    }

    public void saveObjectUser(String key, User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        addValue(key,json);
    }

    private void addValue(String key, String json) {
        sharedPreferences.edit().putString(key, json).commit();
    }

    public User getObjectUser(String key){

        Gson gson = new Gson();
        String json = sharedPreferences.getString(key,"");
        User user = gson.fromJson(json,User.class);
        return  user;
    }

    public void deleteObject(String key){
        sharedPreferences.edit().remove(key).commit();
    }

}
