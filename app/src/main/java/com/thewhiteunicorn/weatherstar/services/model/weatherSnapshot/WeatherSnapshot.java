package com.thewhiteunicorn.weatherstar.services.model.weatherSnapshot;

import com.thewhiteunicorn.weatherstar.services.model.common.Coords;

import java.util.ArrayList;

public class WeatherSnapshot {
    private Coords coord;
    private ArrayList<Weather> weather;
    private String base;
    private WeatherMain main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private WeatherSys sys;
    private int id;
    private String name;
    private int cod;

    public Coords getCoord() {
        return coord;
    }
    public void setCoord(Coords coord) {
        this.coord = coord;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public WeatherMain getMain() {
        return main;
    }
    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }
    public void setDt(long dt) {
        this.dt = dt;
    }

    public WeatherSys getSys() {
        return sys;
    }
    public void setSys(WeatherSys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }
    public void setCod(int cod) {
        this.cod = cod;
    }
}

