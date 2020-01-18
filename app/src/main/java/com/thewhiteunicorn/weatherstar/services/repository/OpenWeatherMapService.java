package com.thewhiteunicorn.weatherstar.services.repository;

import com.thewhiteunicorn.weatherstar.services.model.WeatherSnapshot;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenWeatherMapService {
    String HTTPS_OPEN_WEATHER_MAP_API_URL = "https://samples.openweathermap.org/data/2.5/";

    @GET("weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
    Call<WeatherSnapshot> getCurrentWeather();
}
