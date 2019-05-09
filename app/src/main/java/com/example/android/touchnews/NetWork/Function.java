package com.example.android.touchnews.NetWork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.android.touchnews.Object.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Function {


    public static boolean isNetworkAvailable(Context context)
    {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }


    public static URL createURL(String stringURL)
    {
        URL url =null;

        try {
            url=new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(Function.class.getSimpleName(),"Error Creating URL "+e);
        }

        return url;

    }



    public static String openHttpConnection(URL url){

        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        String jsonResponse = null;

        try {
            urlConnection=(HttpURLConnection) url.openConnection();
//            urlConnection.setConnectTimeout(10000);
//            urlConnection.setReadTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse=readFromInputStream(inputStream);
            }
            else
            {
                Log.e(Function.class.getSimpleName(),"Heloooo"+urlConnection.getResponseCode());
                return null ;

            }
        } catch (IOException e) {
           Log.e(Function.class.getSimpleName(),"Error Connection to server "+e);
        }

      //  urlConnection.disconnect();
        return jsonResponse;

    }

   static public String readFromInputStream(InputStream inputStream) throws IOException {

        StringBuilder output=new StringBuilder();

        if(inputStream!=null)
        {
            InputStreamReader inputStream1=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(inputStream1);
            String line=reader.readLine() ;

            while (line!=null){
                output.append(line);
                line=reader.readLine();
            }

            reader.close();
        }


    return output.toString();
    }

    public static ArrayList <News> extractFeaturesFromJson(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        ArrayList<News> news = new ArrayList<>();

        try {


            JSONObject object = new JSONObject(jsonResponse);

            JSONArray articles = object.getJSONArray("articles");

            for (int j = 0; j < articles.length(); j++) {

                String title = articles.getJSONObject(j).getString("title");

                String description = articles.getJSONObject(j).getString("description");

                String url = articles.getJSONObject(j).getString("url");

                String img = articles.getJSONObject(j).getString("urlToImage");

                String time = articles.getJSONObject(j).getString("publishedAt");

                JSONObject source = articles.getJSONObject(j).getJSONObject("source");

                String author = source.getString("name");


                News news1 = new News(title, author, img, description, time, url);
                news.add(news1);

            }

        } catch (JSONException e) {
            Log.e(Function.class.getSimpleName(), "Problem parsing the earthquake JSON results", e);
     }

            return news;
        }
    }





