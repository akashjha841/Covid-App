package com.example.android.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.canada.ca/en/public-health/services/diseases/coronavirus-disease-covid-19.html");
    }
}