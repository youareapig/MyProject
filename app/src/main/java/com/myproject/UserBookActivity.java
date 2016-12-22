package com.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import utils.Global;

public class UserBookActivity extends AppCompatActivity {
    private WebView webView;
    private Global global;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book);
        global=new Global();
        url=global.getUrl()+"/api.php/Member/deal";
        webView= (WebView) findViewById(R.id.userbook_web);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
        findViewById(R.id.userbook_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
