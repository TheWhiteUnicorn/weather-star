package com.thewhiteunicorn.weatherstar.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.FragmentCitiesListBinding;
import com.thewhiteunicorn.weatherstar.view.adapter.CitiesAdapter;
import com.thewhiteunicorn.weatherstar.view.callback.CityClickCallback;
import com.thewhiteunicorn.weatherstar.view.callback.CityFavouriteCallback;
import com.thewhiteunicorn.weatherstar.viewmodel.CitiesListViewModel;

public class CitiesListFragment extends Fragment {
    private CitiesListViewModel viewModel;
    private CitiesAdapter citiesAdapter;
    private FragmentCitiesListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /*AppDatabase db = App.getInstance().getDatabase();
        CityDao cityDao = db.cityDao();

        cityDao.cleanFavouriesTable();

        FavoriteCity favoriteCity = new FavoriteCity();
        favoriteCity.cityId = 833;fragment_cities
        cityDao.setFavourite(favoriteCity);*/

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities_list, container, false);

        citiesAdapter = new CitiesAdapter(cityClickCallback, cityFavouriteCallback);
        binding.citiesList.setAdapter(citiesAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CitiesListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(CitiesListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getCitiesListObservable().observe(this, cities -> {
            if (cities != null) {
                binding.setIsLoading(false);
                citiesAdapter.setCitiesList(cities);
            }
        });
    }

    private final CityClickCallback cityClickCallback = city -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {

        }
    };

    private final CityFavouriteCallback cityFavouriteCallback = city -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.toggleCityFavourite(city);
        }
    };
}
