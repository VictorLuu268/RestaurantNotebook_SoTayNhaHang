package com.victor.sotaynhahang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.victor.adapter.CustomInfoAdapter;
import com.victor.model.Restaurant;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang xử lý map");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
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
        progressDialog.dismiss();
        mMap = googleMap;

        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("T_RESTAURANT");

        LatLng locate = new LatLng(restaurant.getLongitude(), restaurant.getLatitude());
        Marker marker = mMap.addMarker(new MarkerOptions().position(locate).title(restaurant.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locate,16));
        mMap.setInfoWindowAdapter(new CustomInfoAdapter(MapsActivity.this,restaurant));
        marker.showInfoWindow();
    }
}
