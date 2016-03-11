package com.example.hypertesto.test;

import android.app.Activity;
import android.app.DownloadManager;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyAppWebViewClient());
        myWebView.loadUrl("http://10.196.223.11:8080");
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_fitness_center);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Uri.parse("http://api.openweathermap.org/data/2.5/weather")
                .buildUpon()
                .appendQueryParameter("q", "trento")
                .appendQueryParameter("appid", "f554198a6064e3edb91688bd13f80969")
                .build()
                .toString();

        StringRequest stringRequest = new StringRequest( url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));

                        if (response.contains("Clear")) {


                            mBuilder.setContentTitle("Bel tempo!");

                            mBuilder.setContentText("Perchè non approfitti della giornata per fare attività fisica?");

                            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            // notificationID allows you to update the notification later on.
                            mNotificationManager.notify(999, mBuilder.build());

                        } else {

                            mBuilder.setContentTitle("Nuvoloso");

                            mBuilder.setContentText("Se rimani a casa evita di bivaccare sul divano.");

                            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            // notificationID allows you to update the notification later on.
                            mNotificationManager.notify(999, mBuilder.build());

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                //non faccio niente
            }
        });

        queue.add(stringRequest);
    }


}
