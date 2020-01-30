package com.thewhiteunicorn.weatherstar.services.model.weatherSnapshot;

public class WeatherSys {
    private int type;
    private int id;
    private float message;
    private String country;
    private long sunrize;
    private long sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrize() {
        return sunrize;
    }

    public void setSunrize(long sunrize) {
        this.sunrize = sunrize;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
