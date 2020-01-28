package com.thewhiteunicorn.weatherstar.view.callback;

import com.thewhiteunicorn.weatherstar.services.model.CityWithIsFavourite;

public interface CityFavouriteCallback {
    void onToggle(CityWithIsFavourite city);
}
