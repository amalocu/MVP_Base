package co.com.etn.mvp_base.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.com.etn.mvp_base.dao.ProductDaoImpl;

/**
 * co.com.etn.mvp_base.helper
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.11:04 AM
 */

public class DataBase {

    public Context context;
    public DataBaseHelper dataBaseHelper;

    //DAO´s
    public static ProductDaoImpl dao;

    public DataBase(Context context) {
        this.context = context;
    }

    public DataBase open() throws SQLException{
        try{
            dataBaseHelper =new DataBaseHelper(context);
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
            dao = new ProductDaoImpl(db);
            return this;
        }catch (SQLException ex){
            Log.e("SQL Open:", ex.getMessage());
            throw ex;
        }
    }
    public void close(){
        if(dataBaseHelper!=null) {
            dataBaseHelper.close();
        }
    }
}
