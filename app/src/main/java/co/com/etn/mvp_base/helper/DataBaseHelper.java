package co.com.etn.mvp_base.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import co.com.etn.mvp_base.schemes.IProductScheme;

/**
 * co.com.etn.mvp_base.helper
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.11:07 AM
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context){
        super(context, Constants.DATA_BASE_NAME,null, Constants.DATA_BASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IProductScheme.PRODUCT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Constants.DATA_BASE_NAME,"Actualizando version  a: "+ newVersion);
        db.execSQL(IProductScheme.PRODUCT_TABLE_DROP);
    }
}
