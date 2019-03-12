package com.example.dogmodellist.Room_architecture;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dogmodellist.retrofit.DataModel;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert
    public void insert(Image image);


    @Query("DELETE FROM IMAGE_TABLE")
    void deleteAll();

    @Query("SELECT * from IMAGE_TABLE ORDER BY id DESC LIMIT 1")
    LiveData<Image> getImage();

}
