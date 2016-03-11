package com.example.hypertesto.test;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyAppWebViewClient());
        myWebView.loadUrl("http://10.196.223.11:8080");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_fitness_center);


        String url = Uri.parse("http://api.openweathermap.org/data/2.5/weather")
                .buildUpon()
                .appendQueryParameter("q","Trento")
                .appendQueryParameter("appid", "44db6a862fba0b067b1930da0d769e98")
                .build()
                .toString();

        mBuilder.setContentTitle("Bel tempo!");

        mBuilder.setContentText("Perchè non approfitti della giornata per fare attività fisica?");

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(999, mBuilder.build());
    }
}
