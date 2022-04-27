package com.module.emunicipalcorporation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class OnlineServicesMapsLayout extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    WebView webView;
    private static final int REQUEST_LOCATION = 1;
    String latitude, longitude;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_services_maps_layout);
        Intent i = getIntent();
        String urlelement = i.getStringExtra("urlelement");
        String title =i.getStringExtra("title");
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webview);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OnlineServiceActivity.class));
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(OnlineServicesMapsLayout.this);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(OnlineServicesMapsLayout.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(OnlineServicesMapsLayout.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getCurrentLocation();


        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            fusedLocationProviderClient.getLastLocation().
                    addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            Intent i = getIntent();
                            String urlelement = i.getStringExtra("urlelement");
                            if (location != null) {
                                latitude = String.valueOf(location.getLatitude());
                                longitude = String.valueOf(location.getLongitude());
                                if (latitude.length() != 0 && longitude.length() != 0) {
                                    String url = "https://www.google.com/maps/search/"+urlelement+"/&q=" + latitude + "," + longitude;
                                    webView.getSettings().setJavaScriptEnabled(true);
                                    webView.setWebViewClient(new HelloWebViewClient());
                                    webView.loadUrl(url);

                                }
                            } else {

                                latitude = null;
                                longitude = null;
                            }


                        }
                    });
        }

    }


    static class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}