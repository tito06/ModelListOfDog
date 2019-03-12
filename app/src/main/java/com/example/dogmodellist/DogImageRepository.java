package com.example.dogmodellist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.dogmodellist.Room_architecture.DogImageRoomDatabase;
import com.example.dogmodellist.Room_architecture.Image;
import com.example.dogmodellist.Room_architecture.ImageDao;
import com.example.dogmodellist.main.MainActivity;
import com.example.dogmodellist.retrofit.Api;
import com.example.dogmodellist.retrofit.DataModel;
import com.example.dogmodellist.retrofit.ImageLoad;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogImageRepository {

    private String TAG = "DogImageRepository";
    private ImageDao mimageDao;
    private LiveData<Image> mAllImage;
    Executor executor;
    ImageView imageView;
    Retrofit retrofit;
    Api api;

    DogImageRepository(Application application) {
        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(
                GsonConverterFactory.create()).build();

        api = retrofit.create(Api.class);

        DogImageRoomDatabase db = (DogImageRoomDatabase) DogImageRoomDatabase.getDataBase(application);
        mimageDao = db.imageDao();

        executor = Executors.newSingleThreadExecutor();
    }

    LiveData<Image> getImage() {

     //   Log.d(TAG, "getImage-in");

        loadImage();

        return mimageDao.getImage();
    }

    public void loadImage() {

     //   Log.d(TAG, "loadImage-in");

        Call<DataModel> call = api.getDog();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

         //       Log.d(TAG, "onResponse-" + response.body().getMessage());
                String link = response.body().getMessage();
                final Image image = new Image(link);

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                     //   Log.d(TAG, "insrt");
                        mimageDao.insert(image);
                    }
                });
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });


    }


}