package com.thewhiteunicorn.weatherstar.services.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
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
            "FROM city LEFT JOIN FavoriteCity ON city.id == FavoriteCity.city_id ")
    DataSource.Factory<Integer, CityWithIsFavourite> getCitiesWithIsFavourite();

    @Query("SELECT city.id, name, country, " +
            "CASE WHEN (FavoriteCity.id is null) " +
            "THEN 0 " +
            "ELSE 1 END AS isFavourite " +
            "FROM city LEFT JOIN FavoriteCity ON city.id == FavoriteCity.city_id " +
            "WHERE name LIKE :name ")
    DataSource.Factory<Integer, CityWithIsFavourite> getCitiesWithIsFavouriteByName(String name);

    @Query("SELECT city.id, name, country, " +
            "1 AS isFavourite " +
            "FROM city LEFT JOIN FavoriteCity ON city.id == FavoriteCity.city_id " +
            "WHERE FavoriteCity.id is not Null ")
    LiveData<List<CityWithIsFavourite>> getFavouriteCities();

    @Insert
    void insert(City city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<City> cities);

    @Insert
    void setFavourite(FavoriteCity favoriteCity);

    @Query("DELETE FROM FavoriteCity WHERE city_id = :cityId")
    void unsetFavourite(long cityId);

    @Update
    void update(City city);

    @Delete
    void delete(City city);

    @Query("DELETE FROM city")
    void cleanCitiesTable();

    @Query("DELETE FROM favoritecity")
    void cleanFavouriesTable();
}
