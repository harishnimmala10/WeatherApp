package com.example.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText city;
    EditText state;
    Button submit;
    public final static String CITY_CONSTANT="CITY_VALUE";
    public final static String STATE_CONSTANT="STATE_VALUE";
    public static ArrayList<FavCity> favCities=new ArrayList<>();
    public FavAdapter favAdapter;
    public boolean isConnected()
    {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayFavCities();
        city=(EditText)findViewById(R.id.editText_city);
        state=(EditText)findViewById(R.id.editText_state);
        submit=(Button)findViewById(R.id.button_submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(city.getText().toString().isEmpty() || state.getText().toString().isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),R.string.invalid,Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, CityWeatherActivity.class);
                    intent.putExtra(CITY_CONSTANT, city.getText().toString());
                    intent.putExtra(STATE_CONSTANT, state.getText().toString());

                    startActivity(intent);
                }

            }
        });

        ListView lv = (ListView) findViewById(R.id.listView);
        favAdapter = new FavAdapter(this,R.layout.fav_city,favCities);
        lv.setAdapter(favAdapter);
        favAdapter.setNotifyOnChange(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(MainActivity.this, CityWeatherActivity.class);
                intent.putExtra(CITY_CONSTANT,favCities.get(position).city);
                intent.putExtra(STATE_CONSTANT,favCities.get(position).state);
                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                favCities.remove(position);
                Toast.makeText(getBaseContext(),R.string.deleted,Toast.LENGTH_LONG).show();
                favAdapter.notifyDataSetChanged();
                displayFavCities();
                return false;
            }
        });
    }

    void displayFavCities() {
        TextView no_fav = (TextView) findViewById(R.id.no_fav_text);
        if(favCities.size()==0)
        {
            (findViewById(R.id.fav_linearlayout)).setVisibility(View.INVISIBLE);
            no_fav.setText("There is no city in your Favourites");
            return;
        }
        no_fav.setText("Favourites");
        (findViewById(R.id.fav_linearlayout)).setVisibility(View.VISIBLE);
        ListView lv = (ListView) findViewById(R.id.listView);
        favAdapter = new FavAdapter(this,R.layout.fav_city,favCities);
        lv.setAdapter(favAdapter);
        favAdapter.notifyDataSetChanged();
    }

    public static int addFavourites(FavCity f) {

        for(int i=0;i<favCities.size();i++) {
            if(favCities.get(i).getState().toLowerCase().equals(f.getState().toLowerCase()) && favCities.get(i).getCity().toLowerCase().equals(f.getCity().toLowerCase())) {
                favCities.set(i,f);
                return 0;
            }
        }
        Log.d("d/cityadd",f.toString());
            favCities.add(f);
            return 1;

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("d/city","came her");
        for(int i=0;i<favCities.size();i++) {
            Log.d("d/cities",favCities.get(i).getCity());
        }
        displayFavCities();
    }
}
