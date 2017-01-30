package com.example.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by saikd on 10/8/2016.
 */
public class DetailsActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (getIntent().getExtras() != null) {

            Weather weatherData = (Weather) getIntent().getExtras().getSerializable("DETAILS");
            String loc=getIntent().getStringExtra("LOCATION");

            TextView textView_loc=(TextView)findViewById(R.id.textView_currentLoc);
            textView_loc.setText(loc+" ("+weatherData.getTime()+")");

            TextView textView_title = (TextView) findViewById(R.id.detail_cloud);
            textView_title.setText(weatherData.getClimateType());

            TextView textView_cloud = (TextView) findViewById(R.id.detail_cloudy);
            textView_cloud.setText(weatherData.getCondition());

            TextView detail_temp=(TextView)findViewById(R.id.detail_temp);
            detail_temp.setText(weatherData.getTemprature() + " \u2109" );

            TextView detail_dewpoint = (TextView) findViewById(R.id.detail_dewpoint);
            detail_dewpoint.setText(weatherData.getDewPoint()+ " Fahrenheit");

            TextView detail_humidity = (TextView) findViewById(R.id.detail_humidity);
            detail_humidity.setText(weatherData.getHumidity()+"%");

            TextView detail_pressure = (TextView) findViewById(R.id.detail_pressure);
            detail_pressure.setText(weatherData.getPressure() + " hPa");

            TextView details_feelsLike = (TextView) findViewById(R.id.details_feelsLike);
            details_feelsLike.setText(weatherData.getFeelsLike() + " Fahrenheit");

            String direction=weatherData.getDirection();
            direction=direction.replaceAll("W","West");
            direction=direction.replaceAll("E","East");
            direction=direction.replaceAll("N","North");
            direction=direction.replaceAll("S","South");

            TextView detail_windy = (TextView) findViewById(R.id.detail_windy);
            detail_windy.setText(weatherData.getWindSpeed()+"mph , " + weatherData.getDegree() + direction );

            TextView max = (TextView) findViewById(R.id.max_temp);
            max.setText(Weather.maximumTemp+ " Fahrenheit");

            TextView min = (TextView) findViewById(R.id.min_temp);
            min.setText(Weather.minimumTemp +  " Fahrenheit");




            ImageView imageView_icon = (ImageView) findViewById(R.id.imageView);
            Picasso.with(DetailsActivity.this).load(weatherData.getIconURL()).resize(150,100).into(imageView_icon);
        }
    }


}
