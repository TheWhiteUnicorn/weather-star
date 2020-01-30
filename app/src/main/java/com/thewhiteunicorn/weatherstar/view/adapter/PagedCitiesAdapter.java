package com.thewhiteunicorn.weatherstar.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.CityListItemBinding;
import com.thewhiteunicorn.weatherstar.services.model.CityWithIsFavourite;
import com.thewhiteunicorn.weatherstar.view.callback.CityClickCallback;
import com.thewhiteunicorn.weatherstar.view.callback.CityFavouriteCallback;

public class PagedCitiesAdapter extends PagedListAdapter<CityWithIsFavourite, PagedCitiesAdapter.CitiesViewHolder> {
    @Nullable
    private final CityClickCallback cityClickCallback;
    @Nullable
    private final CityFavouriteCallback cityFavouriteCallback;


    public PagedCitiesAdapter(@Nullable CityClickCallback cityClickCallback,
                              @Nullable CityFavouriteCallback cityFavouriteCallback) {
        super(CityWithIsFavourite.DIFF_CALLBACK);
        this.cityClickCallback = cityClickCallback;
        this.cityFavouriteCallback = cityFavouriteCallback;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CityListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.city_list_item,
                        parent, false);

        binding.setCallbackListItemClick(cityClickCallback);
        binding.setCallbackFavouriteClick(cityFavouriteCallback);

        return new CitiesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PagedCitiesAdapter.CitiesViewHolder holder, int position) {
        holder.binding.setCity(getItem(position));
        holder.binding.executePendingBindings();
    }

    static class CitiesViewHolder extends RecyclerView.ViewHolder {

        final CityListItemBinding binding;

        CitiesViewHolder(CityListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
