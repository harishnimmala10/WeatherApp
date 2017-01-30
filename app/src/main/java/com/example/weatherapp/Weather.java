package com.example.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by saikd on 10/7/2016.
 */
public class Weather implements Serializable {

    String time,temprature,dewPoint,condition,iconURL,windSpeed,direction
            ,climateType,humidity,feelsLike,pressure,city,state,degree;

    static String maximumTemp,minimumTemp;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemprature() {
        return temprature;
    }

    public void setTemprature(String temprature) {
        this.temprature = temprature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(String maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public String getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(String minimumTemp) {
        this.minimumTemp = minimumTemp;
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

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", temprature='" + temprature + '\'' +
                ", dewPoint='" + dewPoint + '\'' +
                ", condition='" + condition + '\'' +
                ", iconURL='" + iconURL + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", direction='" + direction + '\'' +
                ", climateType='" + climateType + '\'' +
                ", humidity='" + humidity + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", pressure='" + pressure + '\'' +
                ", maximumTemp='" + maximumTemp + '\'' +
                ", minimumTemp='" + minimumTemp + '\'' +
                '}';
    }


    public Weather createWeather(JSONObject js) throws JSONException {


        Weather weather = new Weather();
        weather.setClimateType(js.getString("wx"));
        weather.setCondition(js.getString("condition"));
        weather.setDewPoint(js.getJSONObject("dewpoint").getString("english"));
        weather.setDirection(js.getJSONObject("wdir").getString("dir"));
        weather.setDegree(js.getJSONObject("wdir").getString("degrees"));
        weather.setFeelsLike(js.getJSONObject("feelslike").getString("english"));
        weather.setHumidity(js.getString("humidity"));
        weather.setIconURL(js.getString("icon_url"));
        //weather.setMaximumTemp(js.getString(""));
        //weather.setMinimumTemp(js.getString(""));
        weather.setPressure(js.getJSONObject("mslp").getString("english"));
        weather.setTemprature(js.getJSONObject("temp").getString("english"));
       weather.setTime(js.getJSONObject("FCTTIME").getString("civil"));
        weather.setWindSpeed(js.getJSONObject("wspd").getString("english"));

        return weather;
    }



}
