package com.example.abdullahkhan.retrofitapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JavaPlaceholderApi {

    // Now it's time to call the data from fake Json file and show them as in the form
    // of the TextView

    @GET("posts")
    Call<List<Post>> getPosts();


}
