package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.GpsTracker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddLocationActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    View mapView;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private FusedLocationProviderClient fusedLocationClient;
    private MarkerOptions markerOptions;
    private LatLng markerLatlong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        }
        initView();
    }

    private void initView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Button doneBtn = findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  AddLocationActivity.super.onBackPressed();
                Geocoder geocoder;
                List<Address> addresses = new ArrayList<>();
                geocoder = new Geocoder(AddLocationActivity.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(markerLatlong.latitude, markerLatlong.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode()*/

                Intent returnIntent = new Intent();
                if (addresses != null && addresses.size() > 0) {
                    returnIntent.putExtra(Constants.datePassey, addresses.get(0));
                    returnIntent.putExtra("latlong", markerLatlong.latitude + "," + markerLatlong.longitude);
                }
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        if (mapFragment != null)
            mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                markerLatlong = latLng;
                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);


            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                markerLatlong = mMap.getCameraPosition().target;

                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(markerLatlong);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(markerLatlong.latitude + " : " + markerLatlong.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(markerLatlong));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });


        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                 /*   LatLng sydney = new LatLng(latitude, longitude);

                    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

                    markerLatlong = new LatLng(location.getLatitude(), location.getLongitude());
                    markerOptions = new MarkerOptions();
                    markerOptions.position(markerLatlong);
                    //         markerOptions.title(totalAddress); //Here Total Address is address which you want to show on marker
                    mMap.clear();

                    markerOptions.icon(
                            BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_RED));

                    markerOptions.getPosition();
                    Marker mCurrLocationMarker = mMap.addMarker(markerOptions);

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLatlong));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                    if (mapView != null &&
                            mapView.findViewById(Integer.parseInt("1")) != null) {
                        // Get the button view
                        View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                        // and next place it, on bottom right (as Google Maps app)
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                                locationButton.getLayoutParams();
                        // position on right bottom
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                        layoutParams.setMargins(0, 0, 30, 30);
                    }
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onMapReady(mMap);   //added by me...
                } else {
                    // Permission Denied
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// todo: goto back activity from here
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}