package co.com.etn.mvp_base.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.PhoneList;
import co.com.etn.mvp_base.models.Products;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private String LOG_TAG ="MAPA";
    private GoogleMap mMap;
    private Customers customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        customer = (Customers) getIntent().getSerializableExtra("CUSTOMER");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(checkPlayServices()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_json));
        ArrayList<LatLng> points = new ArrayList<LatLng>();
        // Add a marker in Sydney and move the camera
            for(PhoneList phone :customer.getPhoneList()) {
                LatLng point = new LatLng(phone.getLocation().getCoordinates().get(0), phone.getLocation().getCoordinates().get(1));
                createMarker(phone.getLocation().getCoordinates().get(0),phone.getLocation().getCoordinates().get(1), false, false, phone.getNumber());
                points.add(point);
            }


            ruta(points);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if(result!= ConnectionResult.SUCCESS){
            if(googleApiAvailability.isUserResolvableError(result)){
                googleApiAvailability.getErrorDialog(this,result,9000).show();
            }
            return  false;
        }
        return  true;
    }

    private RoutingListener routinListener = new RoutingListener() {
        @Override
        public void onRoutingFailure(RouteException e) {
            if(e != null) {
                Log.e(LOG_TAG, "Error: " + e.getMessage());
            }else {
                Log.e(LOG_TAG, "Something went wrong, Try again");
            }
        }

        @Override
        public void onRoutingStart() {
            Log.i(LOG_TAG, "onRoutingStart" );
        }

        @Override
        public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {



            //ArrayList<Polyline> polylines = new ArrayList<Polyline>();
            //add route(s) to the map.
            Log.i(LOG_TAG,String.valueOf(route.size()));
            for (int i = 0; i <route.size(); i++) {

                //In case of more than 5 alternative routes

                PolylineOptions polyOptions = new PolylineOptions();
                polyOptions.color(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
                polyOptions.width(10 + i * 3);
                polyOptions.addAll(route.get(i).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                //polylines.add(polyline);

                Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onRoutingCancelled() {
            Log.i(LOG_TAG, "Routing was cancelled.");

        }
    };

    private void ruta(ArrayList<LatLng> points) {
        /// Habilitar el servicio Google Maps Directions API
        //Polyline line = mMap.addPolyline(new PolylineOptions().add(start, end ).width(4).color(Color.BLUE));
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.WALKING)
                .waypoints(points).key(getString(R.string.google_maps_key))
                .optimize(true).withListener(routinListener).alternativeRoutes(true).build();
        routing.execute();
        centerJustPoints(points);
        //3D
        CameraPosition cameraPosition = new CameraPosition.Builder().target(points.get(0)).tilt(90).zoom(13).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                ;


    }

    private void centerJustPoints(ArrayList<LatLng> points) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0; i<points.size();i++){
            builder.include(points.get(i));
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds,50);
        mMap.animateCamera(cameraUpdate);
    }

    private void changeStateControls() {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    private void createMarker(double lat, double lon, boolean center, boolean zoom, String title) {

        LatLng marker = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(marker).title(title).icon(bitMapDescriptorFromVector(this, R.drawable.ic_assistant)).snippet("<B>ogksdñl</B> fjgdñlfgjsñldkgjñskldjfñlkjkl "));
        if(center) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        }
        if(zoom) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));

        }

    }

    private BitmapDescriptor bitMapDescriptorFromVector(Context context, int vector ) {
        Drawable drawable = ContextCompat.getDrawable(context, vector);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888 );
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}
