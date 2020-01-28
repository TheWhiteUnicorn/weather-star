package com.thewhiteunicorn.weatherstar.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thewhiteunicorn.weatherstar.App;
import com.thewhiteunicorn.weatherstar.services.AppDatabase;
import com.thewhiteunicorn.weatherstar.services.model.City;
import com.thewhiteunicorn.weatherstar.services.model.CityWithIsFavourite;
import com.thewhiteunicorn.weatherstar.services.repository.CityDao;

import java.util.List;

public class CitiesListViewModel extends AndroidViewModel {
    private LiveData<List<CityWithIsFavourite>> citiesListObservable;

    public CitiesListViewModel(Application application) {
        super(application);
    }

    public LiveData<List<CityWithIsFavourite>> getCitiesListObservable() {
        if (citiesListObservable == null) {
            loadData();
        }
        return citiesListObservable;
    }

    private void loadData() {
        AppDatabase db = App.getInstance().getDatabase();
        CityDao cityDao = db.cityDao();
        citiesListObservable = cityDao.getCitiesWithIsFavourite();
    }
}
