package com.thewhiteunicorn.weatherstar.services.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "city_id"),
        indices = {@Index(value = "city_id", unique = true)})
public class FavoriteCity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "city_id")
    public long cityId;

    public FavoriteCity(long cityId) {
        this.cityId = cityId;
    }
}
