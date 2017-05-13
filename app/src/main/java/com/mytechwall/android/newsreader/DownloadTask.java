package com.mytechwall.android.newsreader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by arshdeep chimni on 29-03-2017.
 */

public class DownloadTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... urls) {
        URL url;
        HttpsURLConnection urlConnection = null;
        String result = "";
        try {

            url = new URL(urls[0]);
            urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();
            while (data != -1) {

                char c = (char) data;
                result += c;
                data = reader.read();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
