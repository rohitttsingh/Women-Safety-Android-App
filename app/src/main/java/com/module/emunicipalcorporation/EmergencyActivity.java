package com.module.emunicipalcorporation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class EmergencyActivity extends AppCompatActivity {
CardView panicbutton,ambulance,firebrigade,police,womenhelpline,childhelpline,animalhelpline,seniorhelpline,allemergency;
androidx.appcompat.widget.Toolbar toolbar;
    private static final int REQUEST_CALL = 1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        panicbutton=findViewById(R.id.panicbutton);
        ambulance=findViewById(R.id.ambulance);
        firebrigade=findViewById(R.id.firebrigade);
        police=findViewById(R.id.police);
        womenhelpline=findViewById(R.id.womenhelpline);
        childhelpline=findViewById(R.id.childlhelpine);
        animalhelpline=findViewById(R.id.animalhelpline);
        seniorhelpline=findViewById(R.id.seniorhelpline);
        allemergency=findViewById(R.id.allemergency);

        toolbar =  findViewById(R.id.emergencytoolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });
        panicbutton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WomenSafety.class));
            }
        });
        ambulance .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("108");
            }
        });
        firebrigade .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("101");
            }
        });
        police .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("100");
            }
        });
        womenhelpline .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("1091");
            }
        });
        childhelpline .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("1098");
            }
        });
        animalhelpline .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("1962");
            }
        });
        seniorhelpline .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("14567");
            }
        });
        allemergency .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton("112");
            }
        });


    }
    private void callButton(String number) {
        if (ContextCompat.checkSelfPermission(EmergencyActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EmergencyActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }
        else{
            String dial= "tel:"+number;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }
    }
}