package com.thewhiteunicorn.weatherstar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.thewhiteunicorn.weatherstar.services.model.WeatherSnapshot;
import com.thewhiteunicorn.weatherstar.services.repository.WeatherDataProvider;

public class CurrentWeatherSnapshotViewModel extends AndroidViewModel {
    private final LiveData<WeatherSnapshot> currentWeatherSnapshotObservable;
    private final long cityId;

    public ObservableField<WeatherSnapshot> weatherSnapshot = new ObservableField<>();

    public CurrentWeatherSnapshotViewModel(@NonNull Application application, long cityId) {
        super(application);
        this.cityId = cityId;
        currentWeatherSnapshotObservable = WeatherDataProvider.getInstance().getCurrentWeather(); // TODO: city id
    }

    public LiveData<WeatherSnapshot> getCurrentWeatherSnapshotObservable() {
        return currentWeatherSnapshotObservable;
    }

    public void setWeatherSnapshot(WeatherSnapshot snapshot) {
        this.weatherSnapshot.set(snapshot);
    }

    /**
     * A creator is used to inject the city ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final long cityId;

        public Factory(@NonNull Application application, long cityId) {
            this.application = application;
            this.cityId = cityId;
        }

        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass == CurrentWeatherSnapshotViewModel.class) {
                return (T) new CurrentWeatherSnapshotViewModel(application, cityId);
            }
            return null;
        }
    }
}
