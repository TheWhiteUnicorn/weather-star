package com.thewhiteunicorn.weatherstar.services.weatherData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thewhiteunicorn.weatherstar.services.model.WeatherSnapshot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDataProvider {
    private OpenWeatherMapService owmService;
    private static WeatherDataProvider instance;

    private WeatherDataProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenWeatherMapService.HTTPS_OPEN_WEATHER_MAP_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        owmService = retrofit.create(OpenWeatherMapService.class);
    }

    public synchronized static WeatherDataProvider getInstance() {
        if (instance == null) {
            instance = new WeatherDataProvider();
        }
        return instance;
    }

    public LiveData<WeatherSnapshot> getCurrentWeather() {
        final MutableLiveData<WeatherSnapshot> data = new MutableLiveData<>();

        owmService.getCurrentWeather().enqueue(new Callback<WeatherSnapshot>() {
            @Override
            public void onResponse(Call<WeatherSnapshot> call, Response<WeatherSnapshot> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherSnapshot> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
