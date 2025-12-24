package com.jsr.cookiebrowser;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private EditText urlInput;
    private Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.webview);
        urlInput = findViewById(R.id.url_input);
        btnGo = findViewById(R.id.btn_go);

        // إعدادات المتصفح
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // تفعيل جافا سكريبت
        webSettings.setDomStorageEnabled(true); // تفعيل التخزين للمواقع الحديثة
        
        // منع فتح الروابط في متصفح خارجي
        myWebView.setWebViewClient(new WebViewClient());

        // تحميل جوجل كبداية
        myWebView.loadUrl("https://www.google.com");

        btnGo.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            if (!url.startsWith("http")) {
                url = "https://" + url;
            }
            myWebView.loadUrl(url);
        });
    }

    // السماح بالرجوع للخلف داخل المتصفح عند الضغط على زر الرجوع في الهاتف
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
