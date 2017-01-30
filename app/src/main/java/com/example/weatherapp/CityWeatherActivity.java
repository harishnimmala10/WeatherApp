package com.example.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by saikd on 10/7/2016.
 */
public class CityWeatherActivity extends AppCompatActivity implements GetWeatherIteamsAsyncTask.WeatherData{

    ProgressDialog progressDialog;
    final String api_key="a33ae6a5e44fad40";
    String city_value,state_value,temp;
    WeatherAdapter adapter;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city_weather);
        progressDialog=new ProgressDialog(CityWeatherActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading Hourly Data");
        progressDialog.show();

        city_value=getIntent().getStringExtra(MainActivity.CITY_CONSTANT);
        state_value=getIntent().getStringExtra(MainActivity.STATE_CONSTANT);

        location=((TextView)findViewById(R.id.textView_currentLocation));
        location.setText(city_value+", "+state_value);


        new GetWeatherIteamsAsyncTask(CityWeatherActivity.this,progressDialog)
                .execute("http://api.wunderground.com/api/"+api_key+"/hourly/q/"+state_value.replaceAll(" ","_")+"/"+city_value.replaceAll(" ","_")+".json");



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //noinspection ResourceType
        inflater.inflate(R.layout.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item:
                Log.d("menu","came here");
                FavCity favCity = new FavCity();
                favCity.setCity(city_value);
                favCity.setState(state_value);
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                favCity.setDate(dateFormat.format(date));
                favCity.setTemp(temp);
                int status=MainActivity.addFavourites(favCity);
                if(status==0) {
                    Toast.makeText(getApplicationContext(), R.string.updated, Toast.LENGTH_LONG).show();
                }
                if(status==1) {
                    Toast.makeText(getApplicationContext(), R.string.added, Toast.LENGTH_LONG).show();
                }
                Log.d("fav",favCity.toString());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setUpData(final ArrayList<Weather> weathers) {
        String max,min,temp1;
        if(weathers!=null) {
            max=weathers.get(0).getTemprature();
            min=weathers.get(0).getTemprature();
            adapter = new WeatherAdapter(this, R.layout.row_item_layout, weathers);

            adapter.setNotifyOnChange(true);
            ListView listView = ((ListView) findViewById(R.id.listView_data));
            listView.setAdapter(adapter);
            temp = weathers.get(0).getTemprature();
            for(int i=1;i<weathers.size();i++) {
                temp1=weathers.get(i).getTemprature();
                if(Double.parseDouble(temp1)>Double.parseDouble(max)) {
                    max=temp1;
                }
                if(Double.parseDouble(temp1)<Double.parseDouble(min)) {
                    min=temp1;
                }
            }
            Weather.maximumTemp=max;
            Weather.minimumTemp=min;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(CityWeatherActivity.this, DetailsActivity.class);
                    intent.putExtra("DETAILS", (Serializable) weathers.get(i));
                    intent.putExtra("LOCATION", location.getText().toString());
                    startActivity(intent);
                }
            });
            progressDialog.dismiss();
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),R.string.invalid,Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }




    }
}
