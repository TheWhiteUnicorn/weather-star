package com.thewhiteunicorn.weatherstar.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.FragmentWeatherDetailsBinding;
import com.thewhiteunicorn.weatherstar.viewmodel.CurrentWeatherSnapshotViewModel;

public class WeatherDetailsFragment extends Fragment {

    private CurrentWeatherSnapshotViewModel currentWeatherSnapshotViewModel;
    private FragmentWeatherDetailsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentWeatherSnapshotViewModel = ViewModelProviders.of(this)
                .get(CurrentWeatherSnapshotViewModel.class);


        binding.setCurrentWeatherSnapshotViewModel(currentWeatherSnapshotViewModel);
        observeViewModel(currentWeatherSnapshotViewModel);
    }

    private void observeViewModel(final CurrentWeatherSnapshotViewModel viewModel) {
        currentWeatherSnapshotViewModel.getCurrentWeatherSnapshotObservable()
                .observe(this, viewModel::setWeatherSnapshot);
    }
}
