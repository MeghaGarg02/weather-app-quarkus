package org.practice.open.weather.map.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class OpenWeatherMapData {

    Coord CoordObject;
    ArrayList < Weather > weather = new ArrayList < Weather > ();
    private String base;
    Main MainObject;
    private float visibility;
    Wind WindObject;
    
    @JsonIgnore
    Rain rain;
    
    Clouds CloudsObject;
    private float dt;
    Sys SysObject;
    private float timezone;
    private float id;
    private String name;
    private float cod;

    // Getter Methods 

    public Coord getCoord() {
     return CoordObject;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
     return base;
    }

    public Main getMain() {
     return MainObject;
    }

    public float getVisibility() {
     return visibility;
    }

    public Wind getWind() {
     return WindObject;
    }
    
    public Rain getRain() {
         return rain;
    }

    public Clouds getClouds() {
     return CloudsObject;
    }

    public float getDt() {
     return dt;
    }

    public Sys getSys() {
     return SysObject;
    }

    public float getTimezone() {
     return timezone;
    }

    public float getId() {
     return id;
    }

    public String getName() {
     return name;
    }

    public float getCod() {
     return cod;
    }

    // Setter Methods 

    public void setCoord(Coord coordObject) {
     this.CoordObject = coordObject;
    }

    public void setBase(String base) {
     this.base = base;
    }

    public void setMain(Main mainObject) {
     this.MainObject = mainObject;
    }

    public void setVisibility(float visibility) {
     this.visibility = visibility;
    }

    public void setWind(Wind windObject) {
     this.WindObject = windObject;
    }
    
    public void setRain() {
         this.rain = null;
    }

    public void setClouds(Clouds cloudsObject) {
     this.CloudsObject = cloudsObject;
    }

    public void setDt(float dt) {
     this.dt = dt;
    }

    public void setSys(Sys sysObject) {
     this.SysObject = sysObject;
    }

    public void setTimezone(float timezone) {
     this.timezone = timezone;
    }

    public void setId(float id) {
     this.id = id;
    }

    public void setName(String name) {
     this.name = name;
    }

    public void setCod(float cod) {
     this.cod = cod;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
    @Override
    public String toString() {
        return "OpenWeatherMapResponseData [CoordObject=" + CoordObject + ", weather=" + weather + ", base=" + base
                + ", MainObject=" + MainObject + ", visibility=" + visibility + ", WindObject=" + WindObject
                + ", CloudsObject=" + CloudsObject + ", dt=" + dt + ", SysObject=" + SysObject + ", timezone="
                + timezone + ", id=" + id + ", name=" + name + ", cod=" + cod + "]";
    }
}