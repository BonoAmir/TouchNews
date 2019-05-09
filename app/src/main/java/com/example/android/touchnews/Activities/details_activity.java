package com.example.android.touchnews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.android.touchnews.R;

public class details_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);

        final WebView webView=(WebView)findViewById(R.id.webView);
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.loader);

//        progressBar.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String url="";

        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());



//
//int prog=webView.getProgress();
//
//        Log.v(details_activity.class.getSimpleName(),"progresssssss"+prog);
//
//        if (webView.getProgress()==50)
//        {
//            progressBar.setVisibility(View.GONE);
//        }

//
//        webView.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress) {
//                if (progress == 100) {
//                    progressBar.setVisibility(View.GONE);
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//            }
//        });




    }
}
