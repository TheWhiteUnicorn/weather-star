package com.thewhiteunicorn.weatherstar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thewhiteunicorn.weatherstar.services.model.WeatherSnapshot;
import com.thewhiteunicorn.weatherstar.services.weatherData.WeatherDataProvider;

public class CurrentWeatherSnapshotViewModel extends AndroidViewModel {
    private final LiveData<WeatherSnapshot> currentWeatherSnapshotObservable;

    public ObservableField<WeatherSnapshot> weatherSnapshot = new ObservableField<>();

    public CurrentWeatherSnapshotViewModel(@NonNull Application application) {
        super(application);
        currentWeatherSnapshotObservable = WeatherDataProvider.getInstance().getCurrentWeather();
    }

    public LiveData<WeatherSnapshot> getCurrentWeatherSnapshotObservable() {
        return currentWeatherSnapshotObservable;
    }

    public void setWeatherSnapshot(WeatherSnapshot snapshot) {
        this.weatherSnapshot.set(snapshot);
    }
}
