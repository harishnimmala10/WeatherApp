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


public class FavAdapter extends ArrayAdapter<FavCity> {

    List<FavCity> mData;
    Context mContext;
    int mResource;

    public FavAdapter(Context mContext, int mResource, ArrayList<FavCity> mData) {
        super(mContext,mResource,mData);

        this.mData = mData;
            this.mContext = mContext;
            this.mResource = mResource;
        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        FavCity favCity = mData.get(position);

        TextView city_state = (TextView) convertView.findViewById(R.id.city_and_state);
        city_state.setText(favCity.getCity() + " , " + favCity.getState());

       TextView date = (TextView) convertView.findViewById(R.id.updated_date);
        date.setText("Updated on : " + favCity.getDate());

        TextView temp = (TextView) convertView.findViewById(R.id.temperature);
        temp.setText(favCity.getTemp()+" \u2109");


        return convertView;
    }
}
