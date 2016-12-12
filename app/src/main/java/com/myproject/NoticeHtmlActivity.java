package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import utils.Global;

public class NoticeHtmlActivity extends AppCompatActivity {
    private WebView webView;
    private Global global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_html);
        global=new Global();
        Intent intent=getIntent();
        String webUrl=intent.getStringExtra("web");
        webView= (WebView) findViewById(R.id.webview);
        webView.loadUrl(global.getUrl()+webUrl);
        webView.setWebViewClient(new WebViewClient());
        findViewById(R.id.htmlback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
