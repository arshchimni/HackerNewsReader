package com.mytechwall.android.newsreader;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ListView title;
    static ArrayList<String> headline = new ArrayList<>();
    HashSet<String> infoUrls=new HashSet<>();
    String ids = "";
    static ArrayAdapter arrayAdapter;
    ArrayList<String> reulstsCatch=new ArrayList();


    static HashMap<String, String> urlHeadline = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (ListView) findViewById(R.id.newsTitles);
        DownloadTask1 task1 = new DownloadTask1(MainActivity.this);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, headline);
        title.setAdapter(arrayAdapter);


        DownloadTask task = new DownloadTask();
        try {

            ids = task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").get();
            JSONArray jsonArray = new JSONArray(ids);


            for (int i = 0; i < 25; i++) {

                infoUrls.add("https://hacker-news.firebaseio.com/v0/item/" + jsonArray.getString(i) + ".json?print=pretty");
            }
            System.out.println(infoUrls.size());

            urlHeadline.putAll(task1.execute(infoUrls.toArray(new String[infoUrls.size()])).get());

            headline.addAll(urlHeadline.keySet());

            System.out.println(urlHeadline.size()+" HELLO RESULT ");



            System.out.println(urlHeadline.values().size());
            } catch (Exception e) {

            e.printStackTrace();

        }
       title.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent=new Intent(getApplicationContext(),webview.class);
               intent.putExtra("WEB_LOAD_URL",urlHeadline.get(headline.get(i)));
               startActivity(intent);

           }
       });
    }
}
