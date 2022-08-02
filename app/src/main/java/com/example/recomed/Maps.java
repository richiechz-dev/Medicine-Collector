package com.example.recomed;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.recomed.databinding.ActivityMapsBinding;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocalizacion();
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng colosio1 = new LatLng(20.0971844,-98.7616928 );
        mMap.addMarker(new MarkerOptions().position(colosio1).title("Contenedor Colosio"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colosio1));

        LatLng plazaQ = new LatLng(20.0808832, -98.7304326);
        mMap.addMarker(new MarkerOptions().position(plazaQ).title("Contenedor Plaza Q"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(plazaQ));

        LatLng plazaJuarez = new LatLng(20.0808797,-98.7632635);
        mMap.addMarker(new MarkerOptions().position(plazaJuarez).title("Contenedor Plaza Juarez"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(plazaJuarez));

        LatLng reloj = new LatLng(20.0808728,-98.7632635 );
        mMap.addMarker(new MarkerOptions().position(reloj).title("Contenedor Centro Cultural Reloj"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(reloj));

        LatLng galerias = new LatLng(20.0982434,-98.7687817);
        mMap.addMarker(new MarkerOptions().position(galerias).title("Contenedor Galerias"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(galerias));

        LatLng parque = new LatLng(20.0935667,-98.7727255);
        mMap.addMarker(new MarkerOptions().position(parque).title("Contenedor Parque David"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(parque));

        LatLng polideportivo = new LatLng(20.0951963,-98.7684954);
        mMap.addMarker(new MarkerOptions().position(polideportivo).title("Contenedor Polideportivo plata"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(polideportivo));

        LatLng parqueC = new LatLng(20.1196262,-98.7331156);
        mMap.addMarker(new MarkerOptions().position(parqueC).title("Contenedor Parque Hidalgo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(parqueC));

        LatLng huejutla = new LatLng(21.1339682,-98.4145548);
        mMap.addMarker(new MarkerOptions().position(huejutla).title("Tienda"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(huejutla));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) Maps.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title("ubicacion actual"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(15)
                        .bearing(50)
                        .tilt(30)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }
}