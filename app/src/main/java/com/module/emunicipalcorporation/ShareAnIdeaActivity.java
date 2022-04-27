package com.module.emunicipalcorporation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShareAnIdeaActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
String url ="https://shareusidea.netlify.app/";
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_an_idea);
        webView = findViewById(R.id.webview);

        toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new OnlineServicesMapsLayout.HelloWebViewClient());
        webView.loadUrl(url);

    }
    static class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}