package com.thewhiteunicorn.weatherstar.services.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import com.thewhiteunicorn.weatherstar.services.model.City;

import java.util.List;

@Dao
public interface CityDao {
    @Query("SELECT * FROM city")
    LiveData<List<City>> getAll();

    @Query("SELECT * FROM city WHERE id = :id")
    LiveData<City> getById(long id);

    @Query("SELECT COUNT(id) FROM city")
    int getRowCount();

    @Insert
    void insert(City city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<City> cities);

    @Update
    void update(City city);

    @Delete
    void delete(City city);

    @Query("DELETE FROM city")
    void nukeTable();
}
