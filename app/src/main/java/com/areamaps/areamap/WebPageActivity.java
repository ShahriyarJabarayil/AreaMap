package com.areamaps.areamap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageActivity extends AppCompatActivity {
    WebView webView;
    String url="http://area.gov.az";
    Context context=this;
    ProgressDialog progressDialog1;
    CustomWebViewClient customWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        progressDialog1=new ProgressDialog(context);
        progressDialog1.setMessage("Yüklənir...");

        customWebViewClient= new CustomWebViewClient();

        customWebViewClient.progressDialog=progressDialog1;

        webView=findViewById(R.id.id_webView);


        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(customWebViewClient);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(url);
    }
}
