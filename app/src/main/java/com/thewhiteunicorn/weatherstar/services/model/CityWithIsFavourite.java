package com.thewhiteunicorn.weatherstar.services.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class CityWithIsFavourite {
    public long id;
    public String name;
    public String country;
    public boolean isFavourite;

    public static DiffUtil.ItemCallback<CityWithIsFavourite> DIFF_CALLBACK = new DiffUtil.ItemCallback<CityWithIsFavourite>() {
        @Override
        public boolean areItemsTheSame(@NonNull CityWithIsFavourite oldItem, @NonNull CityWithIsFavourite newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CityWithIsFavourite oldItem, @NonNull CityWithIsFavourite newItem) {
            return newItem.id == oldItem.id
                    && Objects.equals(newItem.name, oldItem.name)
                    && newItem.isFavourite == oldItem.isFavourite;
        }
    };
}
