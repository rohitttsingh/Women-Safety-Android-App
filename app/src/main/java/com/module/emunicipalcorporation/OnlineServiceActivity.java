package com.module.emunicipalcorporation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OnlineServiceActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    CardView buslocator, garbasebinlocator,wardofficelocator,urbanhealthcare,toiletlocator,waterdrainage,fireserviceonline,gasbill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_service);

        toolbar =  findViewById(R.id.toolbar);
        buslocator= findViewById(R.id.buslocator);
        garbasebinlocator = findViewById(R.id.garbagebinlocator);
        wardofficelocator=findViewById(R.id.wardofficelocator);
        urbanhealthcare=findViewById(R.id.urbanhealthcenter);
        toiletlocator=findViewById(R.id.toiletlocator);
        waterdrainage = findViewById(R.id.waterdrainage);
        fireserviceonline = findViewById(R.id.fireemergencyservice);
        gasbill=findViewById(R.id.gasbill);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });

        buslocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OnlineServicesMapsLayout.class);
                intent.putExtra("title","Bus Locator");
                intent.putExtra("urlelement","bus+stop+near+me");
                startActivity(intent);
            }
        });

        garbasebinlocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OnlineServicesMapsLayout.class);
                intent.putExtra("title","Garbage Locator");
                intent.putExtra("urlelement","dustbin");
                startActivity(intent);
            }
        });
        urbanhealthcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OnlineServicesMapsLayout.class);
                intent.putExtra("title","Urban Health Care");
                intent.putExtra("urlelement","urban+health+care");
                startActivity(intent);
            }
        });

        wardofficelocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OnlineServicesMapsLayout.class);
                intent.putExtra("title","Ward Office");
                intent.putExtra("urlelement","ward+office");
                startActivity(intent);

            }
        });

        toiletlocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OnlineServicesMapsLayout.class);
                intent.putExtra("title","Toilet Locator");
                intent.putExtra("urlelement","toilet");
                startActivity(intent);

            }
        });
        waterdrainage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Water Drainage Service");
                intent.putExtra("url","https://vmc.gov.in/Department_Waterworks.aspx");
                startActivity(intent);

            }
        });
        fireserviceonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Fire  Emergency Online Service");
                intent.putExtra("url","https://vmc.gov.in/Department_Fire.aspx");
                startActivity(intent);

            }
        });
        gasbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Gas Bill");
                intent.putExtra("url","https://payments.vgl.co.in/");
                startActivity(intent);

            }
        });
    }
}