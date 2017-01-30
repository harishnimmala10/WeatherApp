package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by saikd on 10/7/2016.
 */
public class WeatherUtil {

   static public class WeatherParser{

        static ArrayList<Weather> parseWeatherItems(String inputStream) throws JSONException {

            ArrayList<Weather> weatherList = new ArrayList<>();
            JSONObject root =new JSONObject(inputStream);
            JSONArray jsonArray = root.getJSONArray("hourly_forecast");
            Weather weather =new Weather();
            for(int i=0; i<jsonArray.length();i++){

                JSONObject weatherJsonObject = jsonArray.getJSONObject(i);

                weatherList.add(weather.createWeather(weatherJsonObject));

            }

            return  weatherList;

        }



    }
}
