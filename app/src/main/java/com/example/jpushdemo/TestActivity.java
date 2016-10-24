package com.example.jpushdemo;

import cn.jpush.android.api.JPushInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.by.supernewsapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        WebView webView = (WebView) findViewById(R.id.test_webview);
        webView.setWebViewClient(new WebViewClient());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

//        TextView tv = new TextView(this);
//        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
            // 通过JPushInterface 可以获取所有字段
	        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
	        String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//	        tv.setText("Title : " + title + "  " + "Content : " + content);

            //获取附加字段，字符串，格式为JSON
            String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
            try {
                JSONObject  jsonObject = new JSONObject(json);
                String url = jsonObject.getString("url");
//                Toast.makeText(this,"url: " + url,Toast.LENGTH_SHORT).show();
                webView.loadUrl(url);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
