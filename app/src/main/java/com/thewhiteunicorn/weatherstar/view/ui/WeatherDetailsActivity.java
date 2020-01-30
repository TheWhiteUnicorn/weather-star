package com.thewhiteunicorn.weatherstar.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.ActivityWeatherDetailsBinding;
import com.thewhiteunicorn.weatherstar.viewmodel.CurrentWeatherSnapshotViewModel;


public class WeatherDetailsActivity extends AppCompatActivity {
    public static final String KEY_CITY_ID = "city_id";
    private CurrentWeatherSnapshotViewModel currentWeatherSnapshotViewModel;
    ActivityWeatherDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_details);

        CurrentWeatherSnapshotViewModel.Factory factory =
                new CurrentWeatherSnapshotViewModel.Factory(
                        this.getApplication(),
                        getIntent().getLongExtra(KEY_CITY_ID, 0));

        currentWeatherSnapshotViewModel = ViewModelProviders.of(this, factory)
                .get(CurrentWeatherSnapshotViewModel.class);

        binding.setCurrentWeatherSnapshotViewModel(currentWeatherSnapshotViewModel);
        binding.setIsLoading(true);
        observeViewModel(currentWeatherSnapshotViewModel);
    }

    private void observeViewModel(final CurrentWeatherSnapshotViewModel viewModel) {
        currentWeatherSnapshotViewModel.getCurrentWeatherSnapshotObservable()
                .observe(this, snapshot -> {
                    viewModel.setWeatherSnapshot(snapshot);
                    binding.setIsLoading(false);
                });
    }
}