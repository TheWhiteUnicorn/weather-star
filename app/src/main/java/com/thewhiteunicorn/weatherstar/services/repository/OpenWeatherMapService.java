package com.thewhiteunicorn.weatherstar.services.repository;

import com.thewhiteunicorn.weatherstar.services.model.weatherSnapshot.WeatherSnapshot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
    String HTTPS_OPEN_WEATHER_MAP_API_URL = "https://api.openweathermap.org/data/2.5/";

    @GET("weather?units=metric&appid=cc7f0c1bacefd20e6fd16ebfd198940d")
    Call<WeatherSnapshot> getCurrentWeather(@Query("id") long id);
}
