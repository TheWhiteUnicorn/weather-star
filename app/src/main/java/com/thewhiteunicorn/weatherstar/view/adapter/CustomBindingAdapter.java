package com.thewhiteunicorn.weatherstar.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("isSelected")
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    @BindingAdapter({"weatherCondIconCode"})
    public static void loadImage(ImageView view, String code) {
        Picasso.get().load(String.format("http://openweathermap.org/img/wn/%s@2x.png", code)).fit().into(view);
    }
}
