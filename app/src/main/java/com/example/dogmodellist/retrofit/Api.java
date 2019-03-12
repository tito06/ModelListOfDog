package com.example.dogmodellist.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL ="https://dog.ceo/";

    @GET("api/breeds/image/random")
    Call<DataModel> getDog();



}
