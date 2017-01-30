package com.example.weatherapp;

import java.io.Serializable;


public class FavCity implements Serializable {
    String city,state,temp,date;

    @Override
    public String toString() {
        return "FavCity{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", temp='" + temp + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
