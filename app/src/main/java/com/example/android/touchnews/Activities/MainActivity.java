package com.example.android.touchnews.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.touchnews.Adapter.NewsAdapter;
import com.example.android.touchnews.Object.News;
import com.example.android.touchnews.NetWork.Function;
import com.example.android.touchnews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String API_KEY = "https://newsapi.org/v2/top-headlines?country=eg&apiKey=0e6b9d1a829947d3b08f220ece0e31a0";

    ListView listNews;
    ArrayList<News> arrayList = new ArrayList<News>();
    NewsAdapter madapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listNews = (ListView) findViewById(R.id.listView);

        progressBar = (ProgressBar) findViewById(R.id.prog);


        madapter = new NewsAdapter(this, arrayList);
        listNews.setAdapter(madapter);


        if (Function.isNetworkAvailable(getApplicationContext())) {


            DownloadNews newTask = new DownloadNews();
            newTask.execute();

        } else {
            Toast.makeText(MainActivity.this, "Error there is no Internet Connection", Toast.LENGTH_LONG).show();
        }

//        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           public void onItemClick(AdapterView<?> parent, View view,
//                                   int position, long id) {
//               News nws=arrayList.get(position);
//               Intent i = new Intent(MainActivity.this, details_activity.class);
//              i.putExtra("url", nws.getUrl_link());
//              startActivity(i);
//          }
//        });

    }

    class DownloadNews extends AsyncTask<String, Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... urls) {

            List<News> news = new ArrayList<>();

            URL url1 = Function.createURL(API_KEY);
            String jsonResponse = Function.openHttpConnection(url1);
           news=Function.extractFeaturesFromJson(jsonResponse);
            return news;

        }

        @Override
        protected void onPostExecute(final List<News>news) {
            madapter.clear();
            if (news != null && !news.isEmpty()) {





                madapter.addAll(news);
                progressBar.setVisibility(View.GONE);
                }
            }
        }
    }



