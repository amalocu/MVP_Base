package co.com.etn.mvp_base.schemes;

/**
 * ${PACKAGE_NAME}
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.9:14 AM
 */

public interface IProductScheme {

    String PRODUCT_TABLE = "products";
    String PRODUCT_TABLE_COLUNM_ID = "_id";
    String PRODUCT_TABLE_COLUNM_NAME = "name";
    String PRODUCT_TABLE_COLUNM_DESCRIPTION = "description";
    String PRODUCT_TABLE_COLUNM_PRICE = "price";
    String PRODUCT_TABLE_COLUNM_QUANTITY = "quantity";


    String[] PRODUCT_COLUMNS = new String[]{
            PRODUCT_TABLE_COLUNM_ID, PRODUCT_TABLE_COLUNM_NAME, PRODUCT_TABLE_COLUNM_DESCRIPTION, PRODUCT_TABLE_COLUNM_PRICE, PRODUCT_TABLE_COLUNM_PRICE
    };

    String PRODUCT_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE +
             " ( "+
                PRODUCT_TABLE_COLUNM_ID + "  TEXT PRIMARY KEY, " +
                PRODUCT_TABLE_COLUNM_NAME + " TEXT UNIQUE NOT NULL, " +
                PRODUCT_TABLE_COLUNM_DESCRIPTION + " TEXT, " +
                PRODUCT_TABLE_COLUNM_PRICE + " TEXT, " +
                PRODUCT_TABLE_COLUNM_QUANTITY + " TEXT " +
            " );";

    String PRODUCT_TABLE_DROP = "DROP TABLE IF EXISTS " + PRODUCT_TABLE ;


}
