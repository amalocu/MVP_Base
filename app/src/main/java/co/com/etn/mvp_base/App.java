package co.com.etn.mvp_base;

import android.app.Application;
import android.util.Log;

import co.com.etn.mvp_base.helper.DataBase;

/**
 * co.com.etn.mvp_base
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.11:35 AM
 */

public class App extends Application {

    public static DataBase mdb;

    @Override
    public void onCreate(){
        super.onCreate();
        mdb = new DataBase(this);
        mdb = mdb.open();
        Log.e("SQL Open",":------------------------------->Si abriooooo????");
    }

    @Override
    public void onTerminate(){
        mdb.close();
        super.onTerminate();
    }
}
