package com.module.emunicipalcorporation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ComplainActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    private static final int REQUEST_CALL = 1;

    String url = "https://usercomplain.netlify.app/";
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        toolbar =  findViewById(R.id.toolbar);
        getSupportActionBar(toolbar);
        webView=findViewById(R.id.webview);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                callButton("1800-233-0265");
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new OnlineServicesMapsLayout.HelloWebViewClient());
        webView.loadUrl(url);
    }

    private void getSupportActionBar(Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.call);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.call){
            callButton("1800-233-0265");
        }
        return super.onOptionsItemSelected(item);
    }
    private void callButton(String number) {
        if (ContextCompat.checkSelfPermission(ComplainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ComplainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }
        else{
            String dial= "tel:"+number;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }
    }
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}