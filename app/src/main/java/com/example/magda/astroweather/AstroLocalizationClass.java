package com.example.magda.astroweather;


import com.astrocalculator.AstroCalculator;

public class AstroLocalizationClass {

    public static AstroCalculator.Location location= new AstroCalculator.Location(12,35);
    public static int refreshTime=5;
    public static Double getLatitude(){
        return location.getLatitude();
    }
    public static Double getLongitude(){
        return location.getLongitude();
    }
    public static void setLatitude(Double value){
        location.setLatitude(value);
    }
    public static void setLongitude(Double value){
        location.setLongitude(value);
    }
    public static void setRefreshTime(int value){
        refreshTime = value;
    }
    public static int getRefreshTime(){
        return refreshTime;
    }
}
