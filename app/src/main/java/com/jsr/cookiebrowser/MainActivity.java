package com.jsr.cookiebrowser;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);

        EditText cookieInput = new EditText(this);
        cookieInput.setHint("ضع الكوكيز هنا...");
        layout.addView(cookieInput);

        Button btn = new Button(this);
        btn.setText("دخول بالجلسة");
        layout.addView(btn);

        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        layout.addView(webView);

        setContentView(layout);

        btn.setOnClickListener(v -> {
            String cookies = cookieInput.getText().toString();
            CookieManager.getInstance().setAcceptCookie(true);
            for (String cookie : cookies.split(";")) {
                CookieManager.getInstance().setCookie("https://facebook.com", cookie.trim());
            }
            webView.loadUrl("https://m.facebook.com");
        });
    }
}
