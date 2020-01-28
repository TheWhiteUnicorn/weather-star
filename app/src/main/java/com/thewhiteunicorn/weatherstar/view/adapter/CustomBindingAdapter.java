package com.thewhiteunicorn.weatherstar.view.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("isSelected")
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }
}
