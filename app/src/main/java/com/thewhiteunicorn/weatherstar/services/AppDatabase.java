package com.thewhiteunicorn.weatherstar.services;

import androidx.room.RoomDatabase;

import com.thewhiteunicorn.weatherstar.services.model.City;
import com.thewhiteunicorn.weatherstar.services.model.FavoriteCity;
import com.thewhiteunicorn.weatherstar.services.repository.CityDao;

@androidx.room.Database(entities = {City.class, FavoriteCity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
