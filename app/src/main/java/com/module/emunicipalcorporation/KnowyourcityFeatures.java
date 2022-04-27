package com.module.emunicipalcorporation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KnowyourcityFeatures extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowyourcity_features);
        Intent i = getIntent();
        webView=findViewById(R.id.webview);
        String url = i.getStringExtra("url");
        String title = i.getStringExtra("title");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new OnlineServicesMapsLayout.HelloWebViewClient());
        webView.loadUrl(url);
        toolbar =  findViewById(R.id.toolbar);
        getSupportActionBar(toolbar);
        toolbar.setTitle(title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });


    }

    private void getSupportActionBar(Toolbar toolbar) {
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}