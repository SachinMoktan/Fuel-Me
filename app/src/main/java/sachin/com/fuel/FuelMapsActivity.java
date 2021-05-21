package sachin.com.fuel;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FuelMapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng nepal = new LatLng(27.720422, 85.356903);
        mMap.addMarker(new MarkerOptions().position(nepal).title("HAARIHAAR Petrol Pump: Distance 14601m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nepal,12));

        LatLng shakti = new LatLng(27.660115, 85.319126);
        mMap.addMarker(new MarkerOptions().position(shakti).title("SHAKTI Petrol Pump: Distance 3600m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shakti,12));

        LatLng bhakti = new LatLng(27.664199, 85.323541);
        mMap.addMarker(new MarkerOptions().position(bhakti).title("Bjhakti Petrol Pump: Ditance 3660m "));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bhakti,12));

        LatLng raktim = new LatLng(27.668557, 85.337922);
        mMap.addMarker(new MarkerOptions().position(raktim).title("SHyaam Petrol Pumop: Distance 4820m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(raktim,12));

        LatLng riddhi = new LatLng(27.642950, 85.340780);
        mMap.addMarker(new MarkerOptions().position(riddhi).title("Ram Petrol Pump: DIstance 2100m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(riddhi,12));

        LatLng siddhi = new LatLng(27.667473, 85.308758);
        mMap.addMarker(new MarkerOptions().position(siddhi).title("RADHA SHYAM Petrol Pump: DIstance 5011m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(siddhi,12));

        LatLng munaa = new LatLng(27.666767, 85.323826);
        mMap.addMarker(new MarkerOptions().position(munaa).title("BUUDHA SUBBA Petrol Pump: Distance 3915m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(munaa,12));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        findViewById(R.id.type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                }
            }
        });mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });
        setMyLocation();

        mMap.setOnMapClickListener(mapClickListener);

    }

    public GoogleMap.OnMapClickListener mapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("New marker" + latLng.latitude + ":" + latLng.longitude));
            mMap.addCircle(new CircleOptions().radius(200).center(latLng).strokeColor(Color.RED));
        }
    };

    public void setMyLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        setMyLocation();

    }


}


