package com.thewhiteunicorn.weatherstar.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thewhiteunicorn.weatherstar.services.repository.CitiesDataLoader;

public class SplashScreenViewModel extends AndroidViewModel {
    private final LiveData<Integer> loadedCities;

    public SplashScreenViewModel(Application application) {
        super(application);

        loadedCities = CitiesDataLoader.getInstance().loadCitiesData();
    }

    public LiveData<Integer> getLoadedCitiesCountObservable() {
        return loadedCities;
    }
}
