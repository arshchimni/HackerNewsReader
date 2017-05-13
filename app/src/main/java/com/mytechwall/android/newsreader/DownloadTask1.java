package com.mytechwall.android.newsreader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import static com.mytechwall.android.newsreader.MainActivity.headline;
import static com.mytechwall.android.newsreader.MainActivity.urlHeadline;

/**
 * Created by arshdeep chimni on 29-03-2017.
 */

public class DownloadTask1 extends AsyncTask<String,Void,HashMap<String,String>>{

    Context c;
    ProgressDialog asyncDialog;
    public DownloadTask1(Context context){
        c=context;
        asyncDialog = new ProgressDialog(c);
    }


    @Override
    protected void onPreExecute() {
        asyncDialog.setMessage("Loading Data");
        asyncDialog.show();
       // super.onPreExecute();
    }

    @Override
    protected HashMap<String,String> doInBackground(String... urls) {





            URL url;
            HttpsURLConnection urlConnection = null;
            HashMap<String,String> result=new HashMap<>();
            String intermediate = "";

            for (int i=0;i<urls.length;i++) {

                try {

                    url = new URL(urls[i]);
                    urlConnection = (HttpsURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    int data = reader.read();
                    while (data != -1) {

                        char c = (char) data;
                        intermediate += c;
                        data = reader.read();
                    }

                     JSONObject object = new JSONObject(intermediate);
                    if(object.has("url")) {

                        result.put(object.optString("title"),object.optString("url"));
                        System.out.println(object.optString("title"));


                    }

                    intermediate="";

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }//test here man

            return result;

    }

    @Override
    protected void onPostExecute(HashMap<String, String> stringStringHashMap) {

        asyncDialog.dismiss();
        super.onPostExecute(stringStringHashMap);
    }
}







