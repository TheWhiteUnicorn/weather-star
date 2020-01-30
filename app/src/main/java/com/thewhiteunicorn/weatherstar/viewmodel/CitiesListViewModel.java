package com.thewhiteunicorn.weatherstar.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
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
    private MutableLiveData<String> searchKeyword = new MutableLiveData<>();

    public CitiesListViewModel(Application application) {
        super(application);
        CityDao cityDao = App.getInstance().getDatabase().cityDao();

        citiesLiveData = Transformations.switchMap(searchKeyword, input -> {
            DataSource.Factory<Integer, CityWithIsFavourite> factory;
            if (input == null || input.equals("") || input.equals("%%")) {
                //check if the current value is empty load all data else search
                factory = cityDao.getCitiesWithIsFavourite();
            } else {
                factory = cityDao.getCitiesWithIsFavouriteByName('%'+input+'%');
            }
            return new LivePagedListBuilder<>(factory, 50).build();
        });
    }

    public LiveData<PagedList<CityWithIsFavourite>> getCitiesLiveData() {
        return citiesLiveData;
    }

    public void setSearchKeyword(String keyword) {
        searchKeyword.setValue(keyword);
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
