package com.example.dogmodellist.Room_architecture;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName  ="image_table")
public class Image {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    private long id;

    @ColumnInfo(name ="image")
    private String image;

    public Image(String image){
        this.image =image;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
