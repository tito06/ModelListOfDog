package com.example.dogmodellist.Room_architecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Image.class}, version =2)

public abstract class DogImageRoomDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();

    private static volatile DogImageRoomDatabase INSTANCE;

     public static DogImageRoomDatabase getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (DogImageRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DogImageRoomDatabase.class,"image_table").fallbackToDestructiveMigration().build();
                }
            }
        }

        return INSTANCE;
    }
}
