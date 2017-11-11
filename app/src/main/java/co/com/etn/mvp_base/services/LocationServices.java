package co.com.etn.mvp_base.services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * co.com.etn.mvp_base.services
 * MVP_Base
 * Created by alexander.vasquez on 24/10/2017.5:29 PM
 */

public class LocationServices extends Service implements LocationListener{
    
    private final Context context;
    private LocationManager locationManager;
    private boolean checkGPS = false;
    private boolean checkNetwork = false;
    private boolean canGetLocation = false;
    private long minTimeForUdate= 60000;
    private long minDistanceForUdate = 50;
    private Location location ;
    private String LOG_TAG = "LocationServices";
    private double latitude;
    private double longitude;


    public LocationServices (Context context){
        this.context=context;
    }
    
    private  void getLocation(){
        try{
            locationManager=(LocationManager)context.getSystemService(LOCATION_SERVICE);
            //Obtener estado del gps y network
            checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            checkNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!checkGPS && !checkNetwork){
                this.canGetLocation=false;
                //TODO Habilidar gps, 
            }else{
                this.canGetLocation=true;
                //check 
                if(checkGPS){
                    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                        //TODO Implementar dialogo solicitando persmisos
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTimeForUdate, minDistanceForUdate, this);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }else if(checkNetwork){
                    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                        //TODO Implementar dialogo solicitando persmisos
                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTimeForUdate, minDistanceForUdate, this);
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);  
                    if(location!=null){
                        latitude=location.getLatitude();
                        longitude=location.getLongitude();
                    }
                        
                }
            }
        }catch (Exception ex){
            Log.e(LOG_TAG,ex.getMessage());
            ex.printStackTrace();
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }


    public boolean isCanGetLocation() {
        return canGetLocation;
    }
}
