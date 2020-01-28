package com.thewhiteunicorn.weatherstar.services.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.thewhiteunicorn.weatherstar.App;
import com.thewhiteunicorn.weatherstar.services.AppDatabase;
import com.thewhiteunicorn.weatherstar.services.model.City;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class CitiesDataLoader {
    private OpenWeatherMapBulkService owmBulkService;
    private static CitiesDataLoader instance;

    private CitiesDataLoader() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new GZIPInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenWeatherMapBulkService.HTTP_OPEN_WEATHER_MAP_BULK_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        owmBulkService = retrofit.create(OpenWeatherMapBulkService.class);
    }

    public synchronized static CitiesDataLoader getInstance() {
        if (instance == null) {
            instance = new CitiesDataLoader();
        }
        return instance;
    }

    public MutableLiveData<Integer> loadCitiesData() {
        final MutableLiveData<Integer> citiesLoadedCount = new MutableLiveData<>();
        citiesLoadedCount.setValue(0);

        owmBulkService.getCitiesList().enqueue(new Callback<List<City>>() {
            @SuppressLint("CheckResult")
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<City>> call, retrofit2.Response<List<City>> response) {
                io.reactivex.Observable.fromCallable(new CallableLoadCities(response.body()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer count) throws Exception {
                                        citiesLoadedCount.setValue(count);
                                    }
                                },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(final Throwable t) throws Exception {
                                        citiesLoadedCount.setValue(-1);
                                    }
                                }
                        );
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.d("SplashScreen", "Error loading cities", t);
                citiesLoadedCount.setValue(-1);
            }
        });
        return citiesLoadedCount;
    }
}

class GZIPInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);

        return response.newBuilder()
                .header("Content-Encoding", "gzip")
                .header("Content-Type", "application/json")
                        .build();
    }
}

class CallableLoadCities implements Callable<Integer> {
    private List<City> cities;

    CallableLoadCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public Integer call() throws Exception {
        AppDatabase db = App.getInstance().getDatabase();
        CityDao cityDao = db.cityDao();
        int dbCitiesCount = cityDao.getRowCount();
        int responseCitiesCount = cities.size();
        if (dbCitiesCount != responseCitiesCount) {
            cityDao.cleanCitiesTable();
            cityDao.insert(cities);
            return cityDao.getRowCount();
        } else {
            return dbCitiesCount;
        }
    }
}
