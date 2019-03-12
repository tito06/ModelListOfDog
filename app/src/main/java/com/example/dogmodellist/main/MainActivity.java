package com.example.dogmodellist.main;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dogmodellist.DogImageRepository;
import com.example.dogmodellist.ImageViewModel;
import com.example.dogmodellist.R;
import com.example.dogmodellist.Room_architecture.DogImageRoomDatabase;
import com.example.dogmodellist.Room_architecture.Image;
import com.example.dogmodellist.retrofit.ImageLoad;

public class MainActivity extends AppCompatActivity {

    ImageButton cross, right;
    ImageView iv;
    ImageViewModel imageViewModel;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);

        cross =findViewById(R.id.bt1);
        right = findViewById(R.id.bt2);


        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);

        imageViewModel.getImage().observe(this, new Observer<Image>(){

            @Override
            public void onChanged(@Nullable Image image) {
                if(image!=null){
                    ImageLoad il = new ImageLoad(image.getImage(), iv);
                    il.execute();
//                    imageViewModel.getImage();
                    //iv.setImageBitmap(im);

                }
            }
        });


        final LifecycleOwner activity = this;


        cross.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

              //  Log.d(TAG, "onclick");

              //  Toast.makeText(MainActivity.this, "Clcikd", Toast.LENGTH_LONG).show();
                imageViewModel.getImage().observe(activity, new Observer<Image>(){


                    @Override
                    public void onChanged(@Nullable Image image) {

                    //    Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_LONG).show();

                    //    Log.d(TAG, "in side onchanged");

                        if(image!=null){

                    //        Log.d(TAG, "runnning if "+image.getImage());
                            ImageLoad il = new ImageLoad(image.getImage(), iv);

                            il.execute();

                    //        Toast.makeText(MainActivity.this, "> imageloading: "+image.getImage(), Toast.LENGTH_LONG).show();

                     //       Log.d(TAG, "runnning if");



                        }
                    }
                });




            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Image Saved", Toast.LENGTH_LONG).show();


                imageViewModel.getImage().observe(activity, new Observer<Image>(){

                    @Override
                    public void onChanged(@Nullable Image image) {
                        if(image!=null){
                            ImageLoad il = new ImageLoad(image.getImage(), iv);
                            il.execute();


                        }
                    }
                });


            }
        });
    }
}
