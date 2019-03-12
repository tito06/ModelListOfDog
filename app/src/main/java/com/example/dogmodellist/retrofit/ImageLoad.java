package com.example.dogmodellist.retrofit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dogmodellist.main.MainActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.constraint.Constraints.TAG;

public class ImageLoad extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;
    public Context context;


    public ImageLoad(Context context){
        this.context =context;
    }


    public ImageLoad(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;

    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        imageView.setImageBitmap(result);
      //  Log.d(TAG,"picture set with="+url+ ", result="+result);
    }
}
