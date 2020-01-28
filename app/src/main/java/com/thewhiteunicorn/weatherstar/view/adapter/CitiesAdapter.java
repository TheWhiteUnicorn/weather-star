package com.thewhiteunicorn.weatherstar.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thewhiteunicorn.weatherstar.R;
import com.thewhiteunicorn.weatherstar.databinding.CityListItemBinding;
import com.thewhiteunicorn.weatherstar.services.model.City;
import com.thewhiteunicorn.weatherstar.view.callback.CityClickCallback;

import java.util.List;
import java.util.Objects;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private List<? extends City> citiesList;

    @Nullable
    private final CityClickCallback cityClickCallback;

    public CitiesAdapter(@Nullable CityClickCallback cityClickCallback) {
        this.cityClickCallback = cityClickCallback;
    }

    public void setCitiesList(final List<? extends City> citiesList) {
        if (this.citiesList == null) {
            this.citiesList = citiesList;
            notifyItemRangeInserted(0, citiesList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return CitiesAdapter.this.citiesList.size();
                }

                @Override
                public int getNewListSize() {
                    return citiesList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return CitiesAdapter.this.citiesList.get(oldItemPosition).id ==
                            citiesList.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    City city = citiesList.get(newItemPosition);
                    City old = citiesList.get(oldItemPosition);
                    return city.id == old.id
                            && Objects.equals(city.name, old.name);
                }
            });
            this.citiesList = citiesList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CityListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.city_list_item,
                        parent, false);

        binding.setCallback(cityClickCallback);

        return new CitiesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesAdapter.CitiesViewHolder holder, int position) {
        holder.binding.setCity(citiesList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return citiesList == null ? 0 : citiesList.size();
    }

    static class CitiesViewHolder extends RecyclerView.ViewHolder {

        final CityListItemBinding binding;

        CitiesViewHolder(CityListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
