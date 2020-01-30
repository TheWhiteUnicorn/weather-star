package com.thewhiteunicorn.weatherstar.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.thewhiteunicorn.weatherstar.App;
import com.thewhiteunicorn.weatherstar.services.AppDatabase;
import com.thewhiteunicorn.weatherstar.services.model.CityWithIsFavourite;
import com.thewhiteunicorn.weatherstar.services.model.FavoriteCity;
import com.thewhiteunicorn.weatherstar.services.repository.CityDao;


public class CitiesListViewModel extends AndroidViewModel {
    private LiveData<PagedList<CityWithIsFavourite>> citiesLiveData;

    public CitiesListViewModel(Application application) {
        super(application);
        CityDao cityDao = App.getInstance().getDatabase().cityDao();
        DataSource.Factory<Integer, CityWithIsFavourite> factory = cityDao.getCitiesWithIsFavourite();
        LivePagedListBuilder<Integer, CityWithIsFavourite> pagedListBuilder =
                new LivePagedListBuilder<>(factory, 50);
        citiesLiveData = pagedListBuilder.build();
    }

    public LiveData<PagedList<CityWithIsFavourite>> getCitiesLiveData() {
        return citiesLiveData;
    }

    public void toggleCityFavourite(CityWithIsFavourite city) {
        AppDatabase db = App.getInstance().getDatabase();
        CityDao cityDao = db.cityDao();
        FavoriteCity favoriteCity = new FavoriteCity(city.id);
        if (city.isFavourite) {
            cityDao.unsetFavourite(favoriteCity.cityId);
        } else {
            cityDao.setFavourite(favoriteCity);
        }
    }
}
