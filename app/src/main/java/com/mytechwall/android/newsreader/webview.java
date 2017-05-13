package com.mytechwall.android.newsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView loader=(WebView) findViewById(R.id.showWeb);
        Intent intent=getIntent();
        String url=intent.getStringExtra("WEB_LOAD_URL");
        loader.getSettings().setJavaScriptEnabled(true);
        loader.setWebViewClient(new WebViewClient());
        loader.loadUrl(url);
    }
}
