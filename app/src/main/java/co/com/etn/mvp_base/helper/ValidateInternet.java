package co.com.etn.mvp_base.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by alexander.vasquez on 16/09/2017.
 */

public class ValidateInternet implements IValidateItnernet {

    private Context context;

    public ValidateInternet(Context context){
        this.context=context;
    }

    @Override
    public boolean isConnected() {
        boolean res = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        ///Validar que esta conectado o intes intentado conectar que este conectado y esta desponible.
        res = networkInfo!=null && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable() && networkInfo.isConnected();
        return res;
    }
}
