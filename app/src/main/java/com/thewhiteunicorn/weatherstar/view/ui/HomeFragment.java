package com.thewhiteunicorn.weatherstar.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.FragmentFavouriteCitiesBinding;
import com.thewhiteunicorn.weatherstar.view.adapter.CitiesAdapter;
import com.thewhiteunicorn.weatherstar.view.callback.CityClickCallback;
import com.thewhiteunicorn.weatherstar.view.callback.CityFavouriteCallback;
import com.thewhiteunicorn.weatherstar.viewmodel.FavouriteCitiesViewModel;

import static com.thewhiteunicorn.weatherstar.view.ui.WeatherDetailsActivity.KEY_CITY_ID;

public class HomeFragment extends Fragment {
    private FavouriteCitiesViewModel viewModel;
    private CitiesAdapter citiesAdapter;
    private FragmentFavouriteCitiesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_cities, container, false);

        citiesAdapter = new CitiesAdapter(cityClickCallback, cityFavouriteCallback);
        binding.favouriteCitiesList.setAdapter(citiesAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(FavouriteCitiesViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(FavouriteCitiesViewModel viewModel) {
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
            Intent intent = new Intent(getActivity(), WeatherDetailsActivity.class);
            intent.putExtra(KEY_CITY_ID, city.id);
            startActivity(intent);
        }
    };

    private final CityFavouriteCallback cityFavouriteCallback = city -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.toggleCityFavourite(city);
        }
    };
}