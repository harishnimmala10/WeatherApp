package com.example.weatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by saikd on 10/7/2016.
 */
public class GetWeatherIteamsAsyncTask extends AsyncTask<String, Void, ArrayList<Weather>> {

    Context mContext;
    ProgressDialog dialog;
    WeatherData newsData;



    public GetWeatherIteamsAsyncTask(WeatherData context,ProgressDialog progressDialog) {
        this.newsData = context;
        this.dialog=progressDialog;

    }

    @Override
    protected void onPostExecute(ArrayList<Weather> weathers) {
        super.onPostExecute(weathers);
        newsData.setUpData(weathers);

    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpsURLConnection.HTTP_OK) {
                InputStreamReader stream = new InputStreamReader(connection.getInputStream());
                BufferedReader reader=new BufferedReader(stream);
                StringBuilder sb= new StringBuilder();
                String line =reader.readLine();
                while (line!=null){
                    sb.append(line);
                    line =reader.readLine();
                }

                return WeatherUtil.WeatherParser.parseWeatherItems(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static interface WeatherData {
        public void setUpData(ArrayList<Weather> questions);
    }
}
