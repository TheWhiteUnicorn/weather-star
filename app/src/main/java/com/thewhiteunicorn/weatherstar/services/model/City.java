package com.thewhiteunicorn.weatherstar.services.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.thewhiteunicorn.weatherstar.services.model.common.Coords;

@Entity
public class City {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String country;

    @Embedded
    public Coords coords;
}
