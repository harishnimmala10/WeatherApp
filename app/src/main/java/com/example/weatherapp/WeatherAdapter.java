package com.example.weatherapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saikd on 10/8/2016.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {

    List<Weather> mData;
    Context mContext;
    int mResource;

    public WeatherAdapter(Context context, int resource, ArrayList<Weather> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        Weather weather = mData.get(position);

        TextView cloudy = (TextView) convertView.findViewById(R.id.textView_cloud);
        cloudy.setText(weather.getCondition());

        TextView time = (TextView) convertView.findViewById(R.id.textView_time);
        time.setText(weather.getTime());

        TextView temp = (TextView) convertView.findViewById(R.id.textView_temp);
        temp.setText(weather.getTemprature()+" \u2109");


        ImageView iImage = (ImageView) convertView.findViewById(R.id.imageView_icon);
        Picasso.with(mContext).load(weather.getIconURL()).resize(100,80).into(iImage);

        return convertView;
    }
}
