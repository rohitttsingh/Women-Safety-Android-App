package com.module.emunicipalcorporation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KnowYourCityActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    CardView cityatglance,hertiage,plants,otherattration,events,cityinfo,weather,citynews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_city);

        cityatglance =  findViewById(R.id.cityatglance);
        hertiage =  findViewById(R.id.hertiage);
        plants =  findViewById(R.id.plant);
        otherattration =  findViewById(R.id.otherattraction);
        events =  findViewById(R.id.events);
        cityinfo =  findViewById(R.id.cityinfo);
        weather =  findViewById(R.id.weather);
        citynews =  findViewById(R.id.citynews);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });

        cityatglance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","City At Glance");
                intent.putExtra("url","https://vadodara.nic.in/demography/");
                startActivity(intent);

            }
        });
        hertiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Heritage");
                intent.putExtra("url","https://www.gujarattourism.com/central-zone/vadodara/vadodara-heritage-walk.html#:~:text=The%20city%20of%20Vadodara%20(formerly,centuries%20before%20the%20Current%20Era.");
                startActivity(intent);

            }
        });
        plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Plants");
                intent.putExtra("url","https://www.inaturalist.org/places/vadodara#taxon=47126");
                startActivity(intent);
            }
        });
        otherattration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Other Attractions");
                intent.putExtra("url","https://vadodara.nic.in/tourist-places/");
                startActivity(intent);

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","Events");
                intent.putExtra("url","https://announcement.netlify.app/");
                startActivity(intent);

            }
        });
        cityinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","City Information");
                intent.putExtra("url","https://vadodarapost.netlify.app/");
                startActivity(intent);

            }
        });
        citynews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","City News");
                intent.putExtra("url","https://www.divyabhaskar.co.in/local/gujarat/vadodara/");
                startActivity(intent);
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),KnowyourcityFeatures.class);
                intent.putExtra("title","City News");
                intent.putExtra("url","https://www.bbc.com/weather/1253573");
                startActivity(intent);
            }
        });


    }
}