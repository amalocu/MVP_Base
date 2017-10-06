package co.com.etn.mvp_base.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import co.com.etn.mvp_base.models.Products;
import co.com.etn.mvp_base.providers.DbContentProvider;
import co.com.etn.mvp_base.schemes.IProductScheme;

/**
 * co.com.etn.mvp_base.dao
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.10:13 AM
 */

public class ProductDaoImpl extends DbContentProvider implements IProductDao, IProductScheme {

    private Cursor cursor;
    private ContentValues contentValues;

    public ProductDaoImpl(SQLiteDatabase mDb) {
        super(mDb);
    }

    @Override
    public ArrayList<Products> fetchAllProducts() {
        ArrayList<Products> productList = new ArrayList<Products>();
        cursor = super.query(PRODUCT_TABLE, PRODUCT_COLUMNS, null, null, PRODUCT_TABLE_COLUNM_NAME);
        if(cursor!=null){
            cursor.moveToFirst();
            while(cursor!=null && !cursor.isAfterLast()){
                Products product = cursorToEntity(cursor);
                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return productList;
    }

    @Override
    public boolean createProduct(Products product) {
        boolean response = false;
        long rowId = 0;
        try{
            setContentValues(product);
            rowId = super.insert(PRODUCT_TABLE, getContentValues() );
            if(rowId>-1){
                response=true;
            }
        }catch (SQLiteException ex){
            Log.e("DbErrorCreateProduct",ex.getMessage());
        }

        return response;
    }

    public ContentValues getContentValues() {
        return contentValues;
    }

    public void setContentValues(Products product) {
        this.contentValues = new ContentValues();
        this.contentValues.put(PRODUCT_TABLE_COLUNM_ID,product.getId());
        this.contentValues.put(PRODUCT_TABLE_COLUNM_NAME,product.getName());
        this.contentValues.put(PRODUCT_TABLE_COLUNM_DESCRIPTION,product.getDescription());
        this.contentValues.put(PRODUCT_TABLE_COLUNM_PRICE,product.getPrice());
        this.contentValues.put(PRODUCT_TABLE_COLUNM_QUANTITY,product.getQuantity());
    }

    @Override
    protected Products cursorToEntity(Cursor cursor) {
        Products product = new Products() ;
        int index;
        if(cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_ID)>-1) {
            index = cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_ID);
            product.setId(cursor.getString(index));
        }
        if(cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_NAME)>-1) {
            index = cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_NAME);
            product.setName(cursor.getString(index));
        }
        if(cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_DESCRIPTION)>-1) {
            index = cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_DESCRIPTION);
            product.setDescription(cursor.getString(index));
        }
        if(cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_PRICE)>-1) {
            index = cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_PRICE);
            product.setPrice(cursor.getString(index));
        }
        if(cursor.getColumnIndex(PRODUCT_TABLE_COLUNM_QUANTITY)>-1) {
            index = cursor.getColumnIndexOrThrow(PRODUCT_TABLE_COLUNM_QUANTITY);
            product.setQuantity(cursor.getString(index));
        }
        return product;
    }
}
