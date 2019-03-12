package com.example.dogmodellist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.widget.ImageView;

import com.example.dogmodellist.Room_architecture.Image;

public class ImageViewModel extends AndroidViewModel {

    private DogImageRepository mRepository;

    public ImageViewModel(Application application) {
        super(application);
        mRepository = new DogImageRepository(application);
    }

    public LiveData<Image> getImage(){
        return mRepository.getImage();
    }




}
