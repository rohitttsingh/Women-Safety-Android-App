package com.module.emunicipalcorporation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CovidStatisticActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_statistic);

        toolbar =  findViewById(R.id.toolbar);
        webView=findViewById(R.id.webview);
        String url = "https://covid19vadodara.netlify.app/";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new OnlineServicesMapsLayout.HelloWebViewClient());
        webView.loadUrl(url);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });
    }
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}