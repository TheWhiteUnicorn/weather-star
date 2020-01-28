package com.thewhiteunicorn.weatherstar.services.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "city_id"))
public class StarredCity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "city_id")
    public long cityId;
}
