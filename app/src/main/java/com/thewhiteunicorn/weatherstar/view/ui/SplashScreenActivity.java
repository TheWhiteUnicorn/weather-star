package com.thewhiteunicorn.weatherstar.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.ActivitySplashScreenBinding;
import com.thewhiteunicorn.weatherstar.viewmodel.SplashScreenViewModel;


public class SplashScreenActivity extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        binding.setIsLoadFailed(false);

        binding.splashscreenContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        SplashScreenViewModel splashScreenViewModel = ViewModelProviders.of(this)
                .get(SplashScreenViewModel.class);
        observeViewModel(splashScreenViewModel);
    }

    private void observeViewModel(SplashScreenViewModel viewModel) {
        viewModel.getLoadedCitiesCountObservable().observe(this, citiesCount -> {
            if (citiesCount > 0) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else if (citiesCount == -1) {
                binding.setIsLoadFailed(true);
            }
        });
    }
}
