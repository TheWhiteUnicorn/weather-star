package com.thewhiteunicorn.weatherstar.services.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import com.thewhiteunicorn.weatherstar.services.model.City;
import com.thewhiteunicorn.weatherstar.services.model.CityWithIsFavourite;
import com.thewhiteunicorn.weatherstar.services.model.FavoriteCity;

import java.util.List;

@Dao
public interface CityDao {
    @Query("SELECT * FROM city")
    LiveData<List<City>> getAll();

    @Query("SELECT * FROM city WHERE id = :id")
    LiveData<City> getById(long id);

    @Query("SELECT COUNT(id) FROM city")
    int getRowCount();

    @Query("SELECT city.id, name, country, " +
            "CASE WHEN (FavoriteCity.id is null) " +
            "THEN 0 " +
            "ELSE 1 END AS isFavourite " +
            "FROM city LEFT JOIN FavoriteCity ON city.id == FavoriteCity.city_id " +
            "LIMIT 100")
    LiveData<List<CityWithIsFavourite>> getCitiesWithIsFavourite();

    @Insert
    void insert(City city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<City> cities);

    @Insert
    void setFavourite(FavoriteCity favoriteCity);

    @Update
    void update(City city);

    @Delete
    void delete(City city);

    @Query("DELETE FROM city")
    void cleanCitiesTable();

    @Query("DELETE FROM favoritecity")
    void cleanFavouriesTable();
}
