package com.thewhiteunicorn.weatherstar.services.repository;

import com.thewhiteunicorn.weatherstar.services.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface OpenWeatherMapBulkService {
    String HTTP_OPEN_WEATHER_MAP_BULK_API_URL = "http://bulk.openweathermap.org/sample/";

    @Headers({"Content-Type: application/x-gzip",
              "Content-Encoding: gzip"})
    @GET("city.list.json.gz")
    Call<List<City>> getCitiesList();
}
