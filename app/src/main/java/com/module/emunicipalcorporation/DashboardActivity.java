package com.module.emunicipalcorporation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.module.emunicipalcorporation.databinding.ActivityDashboardBinding;

import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardBinding binding;
    CardView emergencybtn,parkingbtn,onlineservicebtn,mycityfeedbtn,knowyourcitybtn,complainbtn;
    CardView womensafety,vehicleparking,cityannouncement,multiplelnguages,cityinfo,chatbotfeature,howtouse;
    TextView txtMarquee,covidstatsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDashboard.toolbars);

        binding.appBarDashboard.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening Chat Section", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // Uri uri = Uri.parse("https://api.whatsapp.com/send/?phone=+917981089620&text&app_absent=0");
                Uri uri = Uri.parse("https://wa.me/message/66MBBOH3OY57N1");                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();


        NavigationView navigationView1= findViewById(R.id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this::onOptionsItemSelected);

        View headerview = navigationView1.getHeaderView(0);
        txtMarquee = (TextView) findViewById(R.id.marqueeText);
        txtMarquee.setSelected(true);

        emergencybtn =  findViewById(R.id.emergency);
        parkingbtn = findViewById(R.id.parkingbtn);
        onlineservicebtn = findViewById(R.id.onlineservice);
        mycityfeedbtn = findViewById(R.id.mycityfeed);
        knowyourcitybtn = findViewById(R.id.knowyourcity);
        complainbtn = findViewById(R.id.complain);
        covidstatsbtn = findViewById(R.id.covidstats);
        womensafety=findViewById(R.id.womensafetybtn);
        vehicleparking=findViewById(R.id.vehicleparking);
        cityannouncement=findViewById(R.id.cityannouncement);
        multiplelnguages=findViewById(R.id.multiplelnguages);
        cityinfo=findViewById(R.id.cityinfo);
        chatbotfeature=findViewById(R.id.whatsappchat);
        howtouse=findViewById(R.id.gotohelpdesk);

        AlertDialog alertDialog;
        emergencybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),EmergencyActivity.class));
            }
        });
        onlineservicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OnlineServiceActivity.class));
            }
        });
        mycityfeedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MyCityFeedActivity.class));
            }
        });
        knowyourcitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KnowYourCityActivity.class));
            }
        });
        complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ComplainActivity.class));
            }
        });
        parkingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),VehicleParkingIssueActivity.class));
            }
        });
        covidstatsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CovidStatisticActivity.class));
            }
        });
        womensafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WomenSafety.class));
            }
        });
        vehicleparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),VehicleParkingIssueActivity.class));

            }
        });
        cityannouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MyCityFeedActivity.class));

            }
        });
        multiplelnguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();

            }
        });
        cityinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KnowYourCityActivity.class));

            }
        });

        chatbotfeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening Chat Section", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               // Uri uri = Uri.parse("https://api.whatsapp.com/send/?phone=+917981089620&text&app_absent=0");
                Uri uri = Uri.parse("https://wa.me/message/66MBBOH3OY57N1");

                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        howtouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HelpDeskActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.translateicon){

            showChangeLanguageDialog();

        }
        else if (id==R.id.other){
            Toast.makeText(getApplicationContext(),"Others",Toast.LENGTH_LONG).show();

        }
        else if (id==R.id.dashboard){
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));


        }
        else if (id==R.id.notification){
            Toast.makeText(getApplicationContext(),"Notification",Toast.LENGTH_LONG).show();

        }
        else if (id==R.id.feedback){
            String url ="https://play.google.com/store/apps/details?id=com.module.imagetotext";
            Uri uri = Uri.parse(url);
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

        }
        else if (id==R.id.shareusidea){
            startActivity(new Intent(getApplicationContext(),ShareAnIdeaActivity.class));

        }
        else if (id==R.id.helpdesk){
            startActivity(new Intent(getApplicationContext(),HelpDeskActivity.class));


        }
        else if (id==R.id.logout){
            Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
            finish();
            System.exit(0);
            System.exit(1);
            System.exit(2);
            System.exit(3);
            System.exit(4);
            System.exit(5);
            System.exit(6);
            System.exit(7);
            System.exit(8);
            System.exit(9);
            System.exit(10);
            System.exit(11);

        }


        return super.onOptionsItemSelected(item);

    }

    private void showChangeLanguageDialog() {
        final String[] listItem =  new String[]{"English","हिंदी","ગુજરાતી"};
//
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        builder.setTitle("Choose Any Language....");
        builder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLanguage(DashboardActivity.this,"en");
                    recreate();
                }
                else if(i==1){
                    setLanguage(DashboardActivity.this,"hi");
                    recreate();
                }
                else if(i==2){
                    setLanguage(DashboardActivity.this,"gu");
                    recreate();

                }
                dialogInterface.dismiss();
            }
        }).show();

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
    public void setLanguage(Activity a, String language){
        Locale locale= new Locale(language);
        locale.setDefault(locale);
        Resources resources= a.getResources();
        Configuration configuration=resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",language);
        editor.apply();
    }
}